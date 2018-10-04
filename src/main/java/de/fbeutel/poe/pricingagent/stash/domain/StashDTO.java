package de.fbeutel.poe.pricingagent.stash.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Builder(toBuilder = true)
public class StashDTO {

    @JsonProperty("public")
    private final boolean open;

    @JsonProperty("id")
    private final String stashId;

    @JsonProperty("items")
    private final List<ItemDTO> items;

    public Stash toStash() {
        return Stash.builder()
                .id(new ObjectId().toString())
                .creationDate(new Date())
                .open(open)
                .stashId(stashId)
                .items(items.stream()
                        .map(ItemDTO::toItem)
                        .collect(toList()))
                .build();
    }
}
