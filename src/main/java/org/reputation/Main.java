package org.reputation;

import org.reputation.ml.classifier.MLClassifierComponentImpl;
import org.reputation.ml.config.MLClassifierConfig;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        MLClassifierConfig config = () -> "./rndforest2.model";
        MLClassifierComponentImpl randomForest = new MLClassifierComponentImpl(config);
        int correct = 0;
        List<String> badLinks = Files.readAllLines(Paths.get("./ALL-phishing-links.txt"));
        for (int i = 0; i < badLinks.size(); i++) {
            String badLink = badLinks.get(i);
            double result = randomForest.classify(badLink);
            if (result > 0.50) {
                correct++;
            }
            if (i != 0 && i % 50000 == 0) {
                System.out.printf("correct: [%d]; from: [%d]%n", correct, i);
            }
        }
    }
}
