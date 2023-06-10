package org.reputation.service;

import org.apache.commons.lang3.Validate;
import org.reputation.ml.classifier.MLClassifierComponent;
import org.reputation.ml.exception.ClassificationFailedException;
import org.reputation.persistence.UrlReputationCache;

public class UrlClassifier implements ClassifierService {

    private final MLClassifierComponent mlClassifier;
    private final UrlReputationCache cache;

    public UrlClassifier(MLClassifierComponent mlClassifier,
                         UrlReputationCache cache) {
        Validate.notNull(mlClassifier);
        Validate.notNull(cache);
        this.mlClassifier = mlClassifier;
        this.cache = cache;
    }

    @Override
    public double classify(String url) throws ClassificationFailedException {
        Double resultFromCache = cache.get(url);
        if (resultFromCache != null) {
            // cache hit stat
            return resultFromCache;
        }
        double resultFromClassifier = mlClassifier.classify(url);
        cache.put(url, resultFromClassifier); // todo async
        return resultFromClassifier;
    }
}
