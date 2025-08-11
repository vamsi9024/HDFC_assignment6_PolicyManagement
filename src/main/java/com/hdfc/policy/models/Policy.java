package com.hdfc.policy.models;


public abstract class Policy {

    private String policyId;
    private double premiumAmount;
    private int termYears;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public int getTermYears() {
        return termYears;
    }

    public void setTermYears(int termYears) {
        this.termYears = termYears;
    }

    public Policy(String policyId, double premiumAmount, int termYears) {
        this.policyId = policyId;
        this.premiumAmount = premiumAmount;
        this.termYears = termYears;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyId='" + policyId + '\'' +
                ", premiumAmount=" + premiumAmount +
                ", termYears=" + termYears +
                '}';
    }

    public abstract double calculateMaturityAmount();
}
