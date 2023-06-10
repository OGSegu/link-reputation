import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.reputation.ml.classifier.MLClassifierComponentImpl;

import java.io.InputStream;


@Disabled
class ModelBenchmarkTest {

    @Test
    void test() throws Exception {
        InputStream modelStream = getClass().getClassLoader().getResourceAsStream("rndforest.model");
        MLClassifierComponentImpl randomForest = new MLClassifierComponentImpl(modelStream);

    }

}
