package de.fbeutel.poe.pricingagent.currency.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.fbeutel.poe.pricingagent.currency.repository.Currency.DB_FIELD_NAME;
import static org.springframework.data.mongodb.core.aggregation.BooleanOperators.And.and;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public interface CurrencyRepository extends MongoRepository<Currency, String>, CurrencyRepositoryCustom {
}

interface CurrencyRepositoryCustom {
    public List<Currency> findByFilter(final String name);
}

class CurrencyRepositoryCustomImpl implements CurrencyRepositoryCustom {
    private final MongoOperations mongoOperations;

    public CurrencyRepositoryCustomImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Currency> findByFilter(final String name) {
        final List<Criteria> filters = new ArrayList<>();
        if (name != null && !"".equals(name)) {
            filters.add(where(DB_FIELD_NAME).is(name));
        }
        final Query query = filters.isEmpty() ? new Query() :
                query(new Criteria().andOperator(filters.toArray(new Criteria[0])));

        return mongoOperations.find(query, Currency.class);
    }
}