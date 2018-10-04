package de.fbeutel.poe.pricingagent.stash.domain;

import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public enum PriceType {
    FIXED_PRICE, ASKING_PRICE, SKIP, UNKNOWN;

    private static final Logger logger = LoggerFactory.getLogger(PriceType.class);

    public static PriceType parseString(final String priceTypeSubString) {
        if (priceTypeSubString.equals("price")) {
            return FIXED_PRICE;
        }
        if (priceTypeSubString.equals("b/o")) {
            return ASKING_PRICE;
        }
        if (priceTypeSubString.equals("skip")) {
            return SKIP;
        }
        logger.warn("unknown PriceType!: {}", priceTypeSubString);

        return UNKNOWN;
    }
}
