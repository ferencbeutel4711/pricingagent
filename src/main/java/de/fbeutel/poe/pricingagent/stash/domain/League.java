package de.fbeutel.poe.pricingagent.stash.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public enum League {
    STANDARD, HARDCORE, HARDCORE_DELVE, UNKNOWN;

    private static final Logger logger = LoggerFactory.getLogger(Currency.class);

    public static League parseString(final String leagueString) {
        if (leagueString.equals("Standard")) {
            return STANDARD;
        }
        if (leagueString.equals("Hardcore")) {
            return HARDCORE;
        }
        if (leagueString.equals("Hardcore Delve")) {
            return HARDCORE_DELVE;
        }
        logger.warn("unknown league!: {}", leagueString);

        return UNKNOWN;
    }
}
