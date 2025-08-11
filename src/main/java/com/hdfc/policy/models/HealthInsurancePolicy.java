package com.hdfc.policy.models;

public class HealthInsurancePolicy extends Policy{

    public HealthInsurancePolicy(String policyId, double premiumAmount, int termYears) {
        super(policyId, premiumAmount, termYears);
    }

    @Override
    public double calculateMaturityAmount() {
        return getPremiumAmount()*getTermYears()*1.2;
    }
}