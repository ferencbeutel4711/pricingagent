package de.fbeutel.poe.pricingagent.stash.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class ItemDTO {

    @JsonProperty("league")
    private final String league;

    @JsonProperty("typeLine")
    private final String typeLine;

    @JsonProperty("note")
    private final String note;

    public Item toItem() {
        return Item.builder()
                .id(new ObjectId().toString())
                .creationDate(new Date())
                .league(League.valueOf(league.toUpperCase()))
                .typeLine(typeLine)
                .note(note)
                .price(Price.ofPriceString(note))
                .build();
    }
}
