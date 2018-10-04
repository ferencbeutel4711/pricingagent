package de.fbeutel.poe.pricingagent.stash.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StashRepository extends MongoRepository<Stash, String> {
}
