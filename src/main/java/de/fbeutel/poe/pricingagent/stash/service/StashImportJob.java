package de.fbeutel.poe.pricingagent.stash.service;

import de.fbeutel.poe.pricingagent.stash.domain.Stash;
import de.fbeutel.poe.pricingagent.stash.domain.StashDTO;
import de.fbeutel.poe.pricingagent.stash.domain.StashImportState;
import de.fbeutel.poe.pricingagent.stash.domain.StashesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StashImportJob {
    private static final int IMPORT_DELAY = 1000 * 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(PoeApiConnector.class);

    private static int pagingCounter = 0;

    private final PoeApiConnector poeApiConnector;
    private final StashService stashService;

    public StashImportJob(PoeApiConnector poeApiConnector, StashService stashService) {
        this.poeApiConnector = poeApiConnector;
        this.stashService = stashService;
    }

    @Scheduled(fixedDelay = IMPORT_DELAY)
    public void importStashes() {
        final String currentNextChangeId = stashService.findLatestImportState()
                .map(StashImportState::getNextChangeId)
                .orElse(null);

        final StashesDTO newStashesDTO = poeApiConnector.fetchStashes(currentNextChangeId);

        if (newStashesDTO.getImportState().getNextChangeId().equals(currentNextChangeId)) {
            LOGGER.warn("no new stashes atm");
            return;
        }

        pagingCounter++;
        LOGGER.info("on page {}", pagingCounter);

        if (pagingCounter > 10000) {
            return;
        }

        stashService.createImportState(newStashesDTO.getImportState());

        newStashesDTO.getStashes().stream()
                .map(StashDTO::toStash)
                .filter(Stash::isOpen)
                .filter(Stash::hasItem)
                .filter(Stash::isHardcoreDelve)
                .forEach(stashService::create);
    }
}
