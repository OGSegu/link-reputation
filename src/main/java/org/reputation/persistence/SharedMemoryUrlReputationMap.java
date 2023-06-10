package org.reputation.persistence;

import java.io.IOException;

import one.nio.mem.SharedMemoryStringMap;

class SharedMemoryUrlReputationMap extends SharedMemoryStringMap<Double> {

    public SharedMemoryUrlReputationMap(int capacity, String fileName,
                                        long fileSize, long expirationTime) throws IOException {
        super(capacity, fileName, fileSize, expirationTime);
        setSerializer(Double.class);
    }
}
