package com.ordertracking.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatusTracking {

    public static  final int COLLECTED_OF_STORAGE = 1;
    public static  final int IN_TRANSIT = 2;
    public static  final int DELIVERY_EXCEPTION = 3;
    public static final int DELIVERED = 4;
    private static final List<Integer> statusList = new ArrayList<>(Arrays
            .asList(COLLECTED_OF_STORAGE, IN_TRANSIT, DELIVERY_EXCEPTION, DELIVERED));

    public static List<Integer> validStatusList = List.of(statusList.toArray( new Integer[]{}));

    public StatusTracking() {
    }
}
