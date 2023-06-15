package org.reputation.ml.processor;

public class UrlPreprocessor {

    private static final String HTTP_PROTOCOL = "http://";
    private static final String HTTPS_PROTOCOL = "https://";

    public String preprocess(String url) {
        String preprocessed = url;
        if (preprocessed.startsWith("\""))  {
            preprocessed = preprocessed.substring(1);
        }
        if (preprocessed.endsWith("\"")) {
            preprocessed = preprocessed.substring(0, preprocessed.length() - 1);
        }
        if (preprocessed.startsWith(HTTP_PROTOCOL)) {
            preprocessed = preprocessed.substring(HTTP_PROTOCOL.length());
        } else if (preprocessed.startsWith(HTTPS_PROTOCOL)) {
            preprocessed = preprocessed.substring(HTTPS_PROTOCOL.length());
        }
        return preprocessed;
    }

}
