package com.hdfc.policy.models;

public class LifeInsurancePolicy extends Policy{

    public LifeInsurancePolicy(String policyId, double premiumAmount, int termYears) {
        super(policyId, premiumAmount, termYears);
    }

    @Override
    public double calculateMaturityAmount() {
        return getPremiumAmount()*getTermYears()*1.5;
    }

}