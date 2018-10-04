package de.fbeutel.poe.pricingagent.stash.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Stash {

    @Id
    private final String id;

    @Field("stash_id")
    private final String stashId;

    @Field("public")
    private final boolean open;

    @Field("creation_date")
    private final Date creationDate;

    @Field("items")
    private final List<Item> items;

    public boolean hasItem() {
        return !items.isEmpty();
    }

    public boolean isHardcoreDelve() {
        return items.stream().anyMatch(Item::isHardcoreDelve);
    }
}
