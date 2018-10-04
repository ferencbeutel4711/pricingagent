package de.fbeutel.poe.pricingagent.stash.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class StashesDTO {

    @JsonProperty("next_change_id")
    private final String nextChangeId;

    @JsonProperty("stashes")
    private final List<StashDTO> stashes;

    public StashImportState getImportState() {
        return StashImportState.builder()
                .id(new ObjectId().toString())
                .creationDate(new Date())
                .nextChangeId(nextChangeId)
                .build();
    }
}
