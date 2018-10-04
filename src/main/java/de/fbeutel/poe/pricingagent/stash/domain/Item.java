package de.fbeutel.poe.pricingagent.stash.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

import static de.fbeutel.poe.pricingagent.stash.domain.League.HARDCORE_DELVE;

@Data
@Builder(toBuilder = true)
public class Item {

    @Id
    private final String id;

    @Field("league")
    private final League league;

    @Field("type_line")
    private final String typeLine;

    @Field("note")
    private final String note;

    @Field("price")
    private final Price price;

    @Field("creation_date")
    private final Date creationDate;

    public boolean isHardcoreDelve() {
        return league == HARDCORE_DELVE;
    }
}
