package org.reputation.ml.extractor;

import com.google.common.net.InternetDomainName;
import org.reputation.ml.processor.UrlPreprocessor;

import java.net.MalformedURLException;
import java.net.URL;

public interface DomainExtractor {

    default InternetDomainName getDomainObject(String url) throws MalformedURLException {
        String tempUrl = null;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            tempUrl = "http://" + url;
        }
        URL urlObj = new URL(tempUrl == null ? url : tempUrl);
        String host = urlObj.getHost();
        return InternetDomainName.from(host);
    }

    default String getDomain(String url) throws MalformedURLException {
        String tempUrl = null;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            tempUrl = "http://" + url;
        }
        URL urlObj = new URL(tempUrl == null ? url : tempUrl);
        String host = urlObj.getHost();
        return InternetDomainName.from(host).toString();
    }

}
