package com.hdfc.policy.models;

public class TermInsurancePolicy extends Policy{

    public TermInsurancePolicy(String policyId, double premiumAmount, int termYears) {
        super(policyId, premiumAmount, termYears);
    }

    @Override
    public double calculateMaturityAmount() {
        return getPremiumAmount()*getTermYears();
    }
}
