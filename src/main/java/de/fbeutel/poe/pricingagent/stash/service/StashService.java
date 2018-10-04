package de.fbeutel.poe.pricingagent.stash.service;

import de.fbeutel.poe.pricingagent.stash.domain.Stash;
import de.fbeutel.poe.pricingagent.stash.domain.StashImportState;
import de.fbeutel.poe.pricingagent.stash.domain.StashImportStateRepository;
import de.fbeutel.poe.pricingagent.stash.domain.StashRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class StashService {
    private final StashRepository stashRepository;
    private final StashImportStateRepository stashImportStateRepository;

    public StashService(StashRepository stashRepository, StashImportStateRepository stashImportStateRepository) {
        this.stashRepository = stashRepository;
        this.stashImportStateRepository = stashImportStateRepository;
    }

    public void create(final Stash stash) {
        stashRepository.insert(stash);
    }

    public void createImportState(final StashImportState importState) {
        stashImportStateRepository.insert(importState);
    }

    public Optional<StashImportState> findLatestImportState() {
        return stashImportStateRepository.findLatest();
    }

    public List<Stash> findAll() {
        return stashRepository.findAll();
    }

    @PostConstruct
    public void cleanupDB() {
        stashRepository.deleteAll();
        stashImportStateRepository.deleteAll();
    }
}
