package com.ordertracking.rule.imp;

import com.ordertracking.domain.OrderTracking;
import com.ordertracking.domain.TrackingInformation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.ordertracking.constant.StatusTracking.COLLECTED_OF_STORAGE;

@Component
public class CollectedOfStorage {

    /**
     *
     * @param trackingInformation
     * @return Collected of storage status or empty for invalid change
     */
    public String isEligibleToHistorySave(TrackingInformation trackingInformation) {
        Map<Long, List<OrderTracking>> history = trackingInformation.getHistory();
        int trackingStatusId = trackingInformation.getOrderTracking().getTrackingStatusId();

        if ((history.size() == 0) && (trackingStatusId == COLLECTED_OF_STORAGE)) {
            return "Update status";
        }

        return Strings.EMPTY;
    }
}
