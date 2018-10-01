package de.fbeutel.poe.pricingagent.currency.service;

import de.fbeutel.poe.pricingagent.currency.repository.Currency;
import de.fbeutel.poe.pricingagent.currency.repository.CurrencyRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findByFilter(final String name) {
        return currencyRepository.findByFilter(name);
    }

    @PostConstruct
    public void createInitialData() {
        initialData(100, "chaos").forEach(currencyRepository::insert);
        initialData(10, "exalted").forEach(currencyRepository::insert);
    }

    private List<Currency> initialData(final int amount, final String name) {
        return range(0, amount + 1)
                .mapToObj(index -> Currency.builder()
                        .id(new ObjectId().toString())
                        .name(name)
                        .price(randomPrice())
                        .build())
                .collect(toList());
    }

    private double randomPrice() {
        final Random r = new Random();
        return 0.1 + (10 - 0.1) * r.nextDouble();
    }
}
