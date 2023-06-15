package org.reputation.ml.extractor;

import com.google.common.net.InternetDomainName;
import org.reputation.ml.processor.UrlPreprocessor;

import java.net.MalformedURLException;
import java.net.URL;

public interface DomainExtractor {

//    public static void main(String[] args) throws MalformedURLException {
//        String url = "https://84.203.152.12/libs/www.paypal.com/default.aspxrru=inbox&wa=/update/webscr.php?cmd=_login-run&dispatch=5885d80a13c0db1f1ff80d546411d7f8a8350c132bc41e0934cfc023d4e8f9e5f469a4ea6b748ab07f305ac08a7316c3f469a4ea6b748ab07f305ac08a7316c3";
//        String tempUrl = null;
//        if (!url.startsWith("http://") && !url.startsWith("https://")) {
//            tempUrl = "http://" + url;
//        }
//        URL urlObj = new URL(tempUrl == null ? url : tempUrl);
//        String host = urlObj.getHost();
//        System.out.println(InternetDomainName.from(host));
//    }

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
