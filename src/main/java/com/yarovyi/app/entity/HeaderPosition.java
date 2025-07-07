package com.yarovyi.app.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HeaderPosition {
    private Map<String, Integer> headerPositionMap;

    public HeaderPosition(String[] headers, Set<String> requiredHeaders) {
        this.headerPositionMap = resolveHeaderPositions(headers, requiredHeaders);
    }

    private static Map<String, Integer> resolveHeaderPositions(String[] headers,
                                                                        Set<String> requiredHeaders) {
        Map<String, Integer> headersPosition = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            if (requiredHeaders.contains(headers[i])) {
                headersPosition.put(headers[i], i);
            }
        }
        return headersPosition;
    }

    public Integer getPosition(String fieldName) {
        return this.headerPositionMap.get(fieldName);
    }

}
