package com.ordertracking.rule.imp;

import com.ordertracking.domain.TrackingInformation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import static com.ordertracking.constant.StatusTracking.DELIVERY_EXCEPTION;
import static com.ordertracking.constant.StatusTracking.IN_TRANSIT;

@Component
public class ValidStatusToUpdate {

    /**
     *
     * @param trackingInformation
     * @return Update status when status is in transit or delivery exception
     */
    public String isEligibleToHistorySave(TrackingInformation trackingInformation) {
        int trackingStatusId = trackingInformation.getOrderTracking().getTrackingStatusId();

        if (trackingStatusId == IN_TRANSIT
                || trackingStatusId == DELIVERY_EXCEPTION) {
           return  "Update status";
        }

        return Strings.EMPTY;
    }
}
