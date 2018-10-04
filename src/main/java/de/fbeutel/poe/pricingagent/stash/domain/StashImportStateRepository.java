package de.fbeutel.poe.pricingagent.stash.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import static de.fbeutel.poe.pricingagent.stash.domain.StashImportState.CREATION_DATE_FIELD;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public interface StashImportStateRepository extends MongoRepository<StashImportState, String>, StashImportStateRepositoryCustom {
}

interface StashImportStateRepositoryCustom {
    public Optional<StashImportState> findLatest();
}

class StashImportStateRepositoryCustomImpl implements StashImportStateRepositoryCustom {
    private final MongoOperations mongoOperations;

    public StashImportStateRepositoryCustomImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Optional<StashImportState> findLatest() {
        final Query query = new Query();
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC, CREATION_DATE_FIELD));
        return Optional.ofNullable(mongoOperations.findOne(query, StashImportState.class));
    }
}
