package org.reputation.persistence;

import java.io.IOException;
import javax.annotation.Nullable;
import org.reputation.persistence.сonfig.LinkReputationCacheConfig;

public class UrlReputationCache {

    protected final SharedMemoryUrlReputationMap sharedMemoryMap;

    public UrlReputationCache(LinkReputationCacheConfig cacheConfig) throws IOException {
        this.sharedMemoryMap = new SharedMemoryUrlReputationMap(cacheConfig.getCapacity(), cacheConfig.getFileName(),
                cacheConfig.getFileSize(), cacheConfig.getExpirationTime());
    }

    public boolean put(String url, double result) {
        return sharedMemoryMap.put(url, result);
    }

    @Nullable
    public Double get(String url) {
        return sharedMemoryMap.get(url);
    }

    public long size() {
        return sharedMemoryMap.getCount();
    }

    /**
     * <b>!!! CAREFUL !!! This method must be called before application shutdown, otherwise cache will be corrupted</b>
     */
    public final void close() {
        sharedMemoryMap.close();
    }


}
