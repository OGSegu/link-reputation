package org.reputation.persistence;

import java.io.IOException;

import one.nio.mem.SharedMemoryStringMap;

class SharedMemoryLinkReputationMap extends SharedMemoryStringMap<Double> {

    public SharedMemoryLinkReputationMap(int capacity, String fileName,
                                         long fileSize, long expirationTime) throws IOException {
        super(capacity, fileName, fileSize, expirationTime);
        setSerializer(Double.class);
    }
}
