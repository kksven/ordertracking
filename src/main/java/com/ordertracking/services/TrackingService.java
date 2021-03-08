package com.ordertracking.services;

import com.ordertracking.constant.StatusTracking;
import com.ordertracking.domain.OrderTracking;
import com.ordertracking.domain.TrackingInformation;
import com.ordertracking.exception.StorageException;
import com.ordertracking.repository.TemporalRepository;
import com.ordertracking.rule.UpdateStatusRules;
import com.ordertracking.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrackingService {

    private final StorageService storageService;
    private final TemporalRepository repository;
    private final UpdateStatusRules updateStatusRules;

    public TrackingService(StorageService storageService, TemporalRepository repository, UpdateStatusRules updateStatusRules) {
        this.storageService = storageService;
        this.repository = repository;
        this.updateStatusRules = updateStatusRules;
    }

    /**
     *
     * @param file order information
     */
    public void updateStatus(MultipartFile file){
        OrderTracking orderTracking = storageService.storeTracking(file);
        List<OrderTracking> historyList = new ArrayList<>();
        Map<Long, List<OrderTracking>> history = repository.getHistory();

        //Throw exception when status is out of scope
        throwStorageExceptionWhenStatusIsOutScope(orderTracking.getTrackingStatusId());

        TrackingInformation trackingInformation = trackingInformationBuild(orderTracking, history);

        String statusValidToChange = updateStatusRules.isEligibleToHistorySave(trackingInformation);

        //Throw exception when status change is invalid
        throwStorageExceptionWhenStatusIsInvalid(statusValidToChange);

        historyList.add(orderTracking);
        repository.getHistory().put(orderTracking.getOrderId(), historyList);
    }

    private static TrackingInformation trackingInformationBuild(OrderTracking orderTracking, Map<Long, List<OrderTracking>> history) {
        return TrackingInformation.builder()
                .orderTracking(orderTracking)
                .history(history)
                .build();
    }

    private void throwStorageExceptionWhenStatusIsOutScope(int trackingStatusId){
        if (!isStatusValid(trackingStatusId)) {
            throwStorageException();
        }
    }

    private void throwStorageExceptionWhenStatusIsInvalid(String status) {
        if (status.isEmpty()) {
            throwStorageException();
        }

    }

    private static boolean isStatusValid(int trackingStatusId){
        return StatusTracking.validStatusList.stream()
                .anyMatch(id -> id == trackingStatusId);
    }

    private void throwStorageException() {
        throw new StorageException(
                "Invalid Tracking Status");
    }
}
