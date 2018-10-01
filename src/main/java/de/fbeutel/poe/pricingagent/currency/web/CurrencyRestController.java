package de.fbeutel.poe.pricingagent.currency.web;

import de.fbeutel.poe.pricingagent.currency.repository.Currency;
import de.fbeutel.poe.pricingagent.currency.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/currency", produces = APPLICATION_JSON_VALUE)
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<Currency>> findAll(@RequestParam(required = false) final String name) {
        return ResponseEntity.ok(currencyService.findByFilter(name));
    }
}
