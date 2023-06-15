package org.reputation.ml.extractor;

import com.google.common.base.CharMatcher;

import java.net.MalformedURLException;
import java.net.URL;

public class QueryArgNumbersExtractor implements FeatureExtractor {
    @Override
    public double extract(String url) {
        String tempUrl = null;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            tempUrl = "http://" + url;
        }
        String query;
        try {
            query = new URL(tempUrl == null ? url : tempUrl).getQuery();
        } catch (Exception e) {
            return -1;
        }
        return query == null ? 0 : CharMatcher.is('&').countIn(url);
    }

    @Override
    public String featureName() {
        return "queryArgNumbers";
    }
}
