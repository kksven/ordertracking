package com.ordertracking.rule.imp;

import com.ordertracking.constant.StatusTracking;
import com.ordertracking.domain.TrackingInformation;
import com.ordertracking.exception.StorageException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class OutScopeStatus {

    public String isValid(TrackingInformation trackingInformation) {
        int trackingId = trackingInformation.getOrderTracking().getTrackingStatusId();

        return StatusTracking.validStatusList.stream()
                .filter(id -> id == trackingId)
                .map(result -> Strings.EMPTY)
                .findAny().orElseThrow(()->new StorageException(
                        "Out of Scope Status"));
    }
}
