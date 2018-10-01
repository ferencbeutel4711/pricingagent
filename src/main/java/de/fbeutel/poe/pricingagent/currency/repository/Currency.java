package de.fbeutel.poe.pricingagent.currency.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder(toBuilder = true)
public class Currency {

    public static final String DB_FIELD_NAME = "name";
    public static final String DB_FIELD_PRICE = "price";

    @Id
    private final String id;

    @Field(DB_FIELD_PRICE)
    private final double price;

    @Field(DB_FIELD_NAME)
    private final String name;
}
