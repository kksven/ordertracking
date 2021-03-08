package com.ordertracking.repository;

import com.ordertracking.domain.OrderTracking;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Data
public class TemporalRepository {
    Map<Long, List<OrderTracking>> history = new HashMap<>();
}
