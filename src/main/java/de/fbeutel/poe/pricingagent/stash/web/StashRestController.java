package de.fbeutel.poe.pricingagent.stash.web;

import de.fbeutel.poe.pricingagent.stash.domain.Stash;
import de.fbeutel.poe.pricingagent.stash.service.StashService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stashes")
public class StashRestController {

    private final StashService stashService;

    public StashRestController(StashService stashService) {
        this.stashService = stashService;
    }

    @GetMapping
    public ResponseEntity<List<Stash>> findAll() {
        return ResponseEntity.ok(stashService.findAll());
    }
}
