package org.reputation.persistence.сonfig;

public interface BloomFilterConfig {
    double getFalsePositiveRate();
    int getWorkersAmountToInit();
}
