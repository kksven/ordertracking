package com.ordertracking.rule.imp;

import com.ordertracking.constant.StatusTracking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OutScopeStatusTest {
    @InjectMocks
    OutScopeStatus outScopeStatus;

    @Test
    public void xxx(){

        int trackingId = 4;

        String x = StatusTracking.validStatusList.stream()
                .filter(id -> id == trackingId)
                .map(a -> "a")
                .findAny().orElse("B");

        assertEquals("B", x);

    }
}
