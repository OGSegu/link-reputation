package org.reputation.ml.extractor;

import com.google.common.net.InternetDomainName;

import java.net.MalformedURLException;

public class SubdomainsNumberExtractor implements FeatureExtractor, DomainExtractor {

    @Override
    public double extract(String url) {
        InternetDomainName domain;
        try {
            domain = getDomainObject(url);
        } catch (Exception e) {
            return -1;
        }

        return domain.parts().size();
    }

    @Override
    public String featureName() {
        return "subdomainsNumber";
    }
}
