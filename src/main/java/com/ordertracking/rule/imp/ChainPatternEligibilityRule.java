package com.ordertracking.rule.imp;


import com.ordertracking.domain.TrackingInformation;

import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
public interface ChainPatternEligibilityRule {

    String getValidStatus(TrackingInformation trackingInformation);

    default ChainPatternEligibilityRule appendNext(ChainPatternEligibilityRule nextRule) {

        return  orderStatus ->
                Stream.of(getValidStatus(orderStatus), nextRule.getValidStatus(orderStatus))
                        .filter(status -> !status.isEmpty())
                        .findFirst()
                        .orElse("");
    }

    static ChainPatternEligibilityRule chainPatternEligibilitySave(Function<TrackingInformation, String> isEligibleToSaveStatus){
        return isEligibleToSaveStatus::apply;
    }

    static ChainPatternEligibilityRule collectedOfStorage(CollectedOfStorage collectedOfStorage){
        return chainPatternEligibilitySave(collectedOfStorage::isEligibleToHistorySave);
    }

    static ChainPatternEligibilityRule statusValidToUpdate(ValidStatusToUpdate validStatusToUpdate){
        return chainPatternEligibilitySave(validStatusToUpdate::isEligibleToHistorySave);
    }

    static ChainPatternEligibilityRule delivered(Delivered delivered){
        return chainPatternEligibilitySave(delivered::isEligibleToHistorySave);
    }
}
