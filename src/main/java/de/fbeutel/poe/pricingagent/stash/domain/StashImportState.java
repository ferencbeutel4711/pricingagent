package de.fbeutel.poe.pricingagent.stash.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class StashImportState {

    public static final String CREATION_DATE_FIELD = "creation_date";
    @Id
    private final String id;

    @Field("next_change_id")
    private final String nextChangeId;

    @Field(CREATION_DATE_FIELD)
    private final Date creationDate;
}
