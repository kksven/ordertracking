package com.ordertracking.rule;

import com.ordertracking.domain.TrackingInformation;
import com.ordertracking.rule.imp.ChainPatternEligibilityRule;
import com.ordertracking.rule.imp.CollectedOfStorage;
import com.ordertracking.rule.imp.Delivered;
import com.ordertracking.rule.imp.ValidStatusToUpdate;
import org.springframework.stereotype.Component;

@Component
public class UpdateStatusRules {

    private CollectedOfStorage collectedOfStorage;
    private Delivered delivered;
    private ValidStatusToUpdate validStatusToUpdate;

    public UpdateStatusRules(CollectedOfStorage collectedOfStorage, Delivered delivered, ValidStatusToUpdate validStatusToUpdate) {
        this.collectedOfStorage = collectedOfStorage;
        this.delivered = delivered;
        this.validStatusToUpdate = validStatusToUpdate;
    }

    /**
     *
     * @param trackingInformation
     * @return New status to order or empty fot invalid change
     */
    public String isEligibleToHistorySave(TrackingInformation trackingInformation){

        ChainPatternEligibilityRule chainPatternEligibilityRule = getWorkFlowRules();

        return chainPatternEligibilityRule.getValidStatus(trackingInformation);
    }

    private ChainPatternEligibilityRule getWorkFlowRules() {
        return ChainPatternEligibilityRule.collectedOfStorage(collectedOfStorage)
                .appendNext(ChainPatternEligibilityRule.statusValidToUpdate(validStatusToUpdate)
                .appendNext(ChainPatternEligibilityRule.delivered(delivered)));
    }
}
