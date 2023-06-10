package org.reputation.ml.classifier;

import org.reputation.ml.exception.ClassificationFailedException;

public interface MLClassifierComponent {

    double classify(String url) throws ClassificationFailedException;

}
