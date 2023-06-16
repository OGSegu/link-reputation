import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.reputation.ml.classifier.MLClassifierComponentImpl;
import org.reputation.ml.config.MLClassifierConfig;
import org.reputation.ml.dataset.DefaultDatasetProcessor;
import org.reputation.ml.extractor.FeatureExtractorComponentImpl;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



class ModelBenchmarkTest {

    @Disabled
    @Test
    void test() throws Exception {
        String url = "http://www.subnet.gsmkamera.hu/index.html?option=com_content&view=article&id=11&Itemid=20&fontstyle=f-smaller";
        System.out.println(new URL(url).getQuery());

        MLClassifierConfig config = () -> "./rndforest_16features.model";
        MLClassifierComponentImpl randomForest = new MLClassifierComponentImpl(config);
        System.out.println(randomForest.classify("google.com"));
    }

    @Disabled
    @Test
    void triggerDatasetFeatureExtraction() throws Exception {
        DefaultDatasetProcessor defaultDatasetProcessor = new DefaultDatasetProcessor(new FeatureExtractorComponentImpl());
        List<String> urls = Files.readAllLines(Paths.get("./data.txt"));
        Instances dataSet = defaultDatasetProcessor.getDataSet(urls);
        ConverterUtils.DataSink.write("newData.arff", dataSet);
    }

}
