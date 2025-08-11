package com.hdfc.policy.factory;

import com.hdfc.policy.models.Policy;

public interface PolicyCreator {
    Policy create(String policyId, double premium, int termYears);
}
