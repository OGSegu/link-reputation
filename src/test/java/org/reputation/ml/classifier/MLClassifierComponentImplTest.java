package org.reputation.ml.classifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reputation.ml.exception.ClassificationFailedException;
import weka.classifiers.Classifier;
import weka.core.Instance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MLClassifierComponentImplTest {

    private MLClassifierComponentImpl mlClassifierComponent;
    private Classifier classifier;

    @BeforeEach
    void setUp() throws Exception {
        classifier = mock(Classifier.class);
        mlClassifierComponent = new MLClassifierComponentImpl(classifier);
    }

    @Test
    @DisplayName("Should throw a ClassificationFailedException when an error occurs during classification")
    void classifyThrowsClassificationFailedExceptionOnError() throws Exception {
        String url = "http://ex$2ampzxle.com";
        Exception exception = new Exception("Error occurred during classification");
        when(classifier.distributionForInstance(any(Instance.class))).thenThrow(exception);

        assertThrows(ClassificationFailedException.class, () -> mlClassifierComponent.classify(url));
    }

}