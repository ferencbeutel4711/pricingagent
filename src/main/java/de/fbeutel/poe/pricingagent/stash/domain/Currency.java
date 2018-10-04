package de.fbeutel.poe.pricingagent.stash.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Currency {

    CHAOS, EXALTED, REGAL, ALTERATION, JEWELLERS, FUSING, REGRET, DIVINE, ALCHEMY, CHANCE, VAAL,
    CHROMA, SCOURING, CHISEL, GEMCUTTERS, UNKNOWN, BLESSED;

    private static final Logger logger = LoggerFactory.getLogger(Currency.class);

    public static Currency parseString(final String currencySubString) {
        switch (sanitize(currencySubString.toLowerCase())) {
            case "chaos":
                return CHAOS;
            case "exa":
                return EXALTED;
            case "alt":
                return ALTERATION;
            case "jew":
                return JEWELLERS;
            case "fuse":
                return FUSING;
            case "regret":
                return REGRET;
            case "divine":
                return DIVINE;
            case "alchemy":
            case "alch":
                return ALCHEMY;
            case "vaal":
                return VAAL;
            case "chance":
                return CHANCE;
            case "chisel":
                return CHISEL;
            case "chroma":
            case "chrom":
                return CHROMA;
            case "scouring":
            case "scour":
                return SCOURING;
            case "gcp":
                return GEMCUTTERS;
            case "regal":
                return REGAL;
            case "blessed":
                return BLESSED;
        }
        logger.warn("unknown Currency!: {}", currencySubString);

        return UNKNOWN;
    }

    private static final String sanitize(final String unsanitized) {
        return unsanitized.replaceAll("[^a-zA-Z]+", "");
    }
}
