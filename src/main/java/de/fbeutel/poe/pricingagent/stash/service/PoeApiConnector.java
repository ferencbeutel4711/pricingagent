package de.fbeutel.poe.pricingagent.stash.service;

import de.fbeutel.poe.pricingagent.stash.domain.StashesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.RequestEntity.get;

@Service
public class PoeApiConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoeApiConnector.class);

    private final RestTemplate restTemplate;
    private final String poeApiUrl;
    private final String poeApiStashesPath;

    public PoeApiConnector(final RestTemplateBuilder restTemplateBuilder,
                           @Value("${poe.api.url}") final String poeApiUrl,
                           @Value("${poe.api.stashes.path}") final String poeApiStashesPath) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(2000)
                .setReadTimeout(10000)
                .build();

        this.poeApiUrl = poeApiUrl;
        this.poeApiStashesPath = poeApiStashesPath;
    }

    public StashesDTO fetchStashes(final String changeId) {
        final URI apiUri = UriComponentsBuilder.fromUriString(poeApiUrl + poeApiStashesPath)
                .queryParam("id", changeId)
                .build().toUri();

        final ResponseEntity<StashesDTO> response = restTemplate.exchange(get(apiUri).build(), StashesDTO.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("error during poe api fetching. RC: {} message: {}", response.getStatusCodeValue(), response.getBody());
            return null;
        }

        return response.getBody();
    }
}
