import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.reputation.ml.classifier.MLClassifierComponentImpl;

import java.util.ArrayList;
import java.util.List;

class ModelBenchmarkTest {

    @Test
    void test() throws Exception {
        List<Double> blackHolelist = new ArrayList<>();
        MLClassifierComponentImpl randomForest = new MLClassifierComponentImpl(() -> "naivebayess.model");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            String url = RandomStringUtils.randomAlphabetic(10, 30);
            double result = randomForest.classify(url);
            blackHolelist.add(result);
            System.out.printf("%,.2f%n", result);
        }
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime);
        System.out.println(blackHolelist.subList(0,1));
    }

}
