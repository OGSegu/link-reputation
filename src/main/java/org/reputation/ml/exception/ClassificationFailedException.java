package org.reputation.ml.exception;

public class ClassificationFailedException extends Exception {

    private static final String MESSAGE_FORMAT = "Failed to classify url: [%s]";

    public ClassificationFailedException(String url, Throwable cause) {
        super(String.format(MESSAGE_FORMAT, url), cause);
    }
}
