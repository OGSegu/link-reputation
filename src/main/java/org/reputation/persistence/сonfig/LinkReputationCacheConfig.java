package org.reputation.persistence.сonfig;

public interface LinkReputationCacheConfig {
    int getCapacity();
    String getFileName();
    long getFileSize();
    long getExpirationTime();
}
