package com.ordertracking.rule.imp;


import com.ordertracking.constant.StatusTracking;
import com.ordertracking.domain.OrderTracking;
import com.ordertracking.domain.TrackingInformation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class Delivered {

    /**
     *
     * @param trackingInformation
     * @return Delivered status or empty for invalid change
     */
    public String isEligibleToHistorySave(TrackingInformation trackingInformation) {
        Map<Long, List<OrderTracking>> history = trackingInformation.getHistory();
        OrderTracking orderTracking = trackingInformation.getOrderTracking();

        List<OrderTracking> statusList = Optional.ofNullable(history.get(orderTracking.getOrderId())).orElse(new ArrayList<>());

        if (isNotDelivered(statusList)) {
            return  "Update status";
        }

        return Strings.EMPTY;

    }

    private boolean isNotDelivered(List<OrderTracking> statusList) {
        return !statusList.stream()
                .anyMatch(order -> order.getTrackingStatusId() == StatusTracking.DELIVERED);
    }
}
