package de.fbeutel.poe.pricingagent.stash.domain;

import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.fbeutel.poe.pricingagent.stash.domain.League.HARDCORE;
import static de.fbeutel.poe.pricingagent.stash.domain.PriceType.SKIP;
import static de.fbeutel.poe.pricingagent.stash.domain.PriceType.UNKNOWN;

@Data
@Builder(toBuilder = true)
public class Price {

    private static final Pattern PRICE_REGEX = Pattern.compile("(~)(\\S*) ([-+]?[0-9]*\\.?[0-9]+) (\\S*)");
    private static final String PRICE_FLAG = "~";
    private static final Logger logger = LoggerFactory.getLogger(Currency.class);

    @Id
    private final String id;

    @Field("price_type")
    private final PriceType priceType;

    @Field("currency")
    private final Currency currency;

    @Field("value")
    private final double value;

    public static Price unknownPrice() {
        return Price.builder()
                .priceType(UNKNOWN)
                .value(-1)
                .build();
    }

    public static Price ofPriceString(final String priceString) {
        if (priceString == null) {
            return unknownPrice();
        }

        try {
            final Matcher matcher = PRICE_REGEX.matcher(priceString);

            if (!matcher.find() || !matcher.group(1).equals(PRICE_FLAG)) {
                return unknownPrice();
            }

            final PriceType priceType = PriceType.parseString(matcher.group(2));
            if (priceType == SKIP) {
                return unknownPrice();
            }

            final double value = Double.parseDouble(matcher.group(3));
            final Currency currency = Currency.parseString(matcher.group(4));

            return Price.builder()
                    .priceType(priceType)
                    .value(value)
                    .currency(currency)
                    .build();

        } catch (Exception exception) {
            logger.warn("Exception during price string parsing", exception);
            return unknownPrice();
        }
    }
}
