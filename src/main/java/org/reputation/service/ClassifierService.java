package org.reputation.service;

import org.reputation.ml.exception.ClassificationFailedException;

public interface ClassifierService {

    double classify(String text) throws ClassificationFailedException;

}
