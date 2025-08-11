package com.hdfc.policy.services;

import com.hdfc.policy.models.Customer;
import com.hdfc.policy.models.Policy;
import com.hdfc.policy.repository.Repository;

public class PolicyServices {

    private Repository<Customer, String> customerRepo;

    // Creating Singleton class
    private static PolicyServices INSTANCE;

    public PolicyServices() {
        this.customerRepo = customerRepo;
    }

    // Checking Singleton
    public static PolicyServices getInstance() {
        if (INSTANCE == null) {
            synchronized (PolicyServices.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PolicyServices();
                }
            }
        }
        return INSTANCE;
    }

    // Managing customer

    // add
    public void addCustomer(Customer customer) {
        customerRepo.add(customer.getCustomerId(), customer);
    }

    // get
    public Customer getCustomer(String customerID) {
        return customerRepo.get(customerID);
    }

    // remove customer
    public void removeCustomer(String customerID) {
        customerRepo.remove(customerID);
    }

    // Manage policy

    // add
    public void addPolicy( String customerID, Policy policy){
        Customer customer = customerRepo.get(customerID);
        if(customer != null){
            customer.getPolicies().put(policy.getPolicyId(), policy);
        } else {
            System.out.println("Customer not found " + customerID );
        }
    }

    // get
    public Policy getPolicy (String customerID , String policyID) {
        Customer customer = customerRepo.get(customerID);
        if(customer != null){
            return customer.getPolicies().get(policyID);
        } else {
            return null;
        }
    }

    // remove
    public void removePolicy(String customerID , String policyID){
        Customer customer = customerRepo.get(customerID);
        if(customer!= null){
            customer.getPolicies().remove(policyID);
        }
    }

    // Maturity Calculation

    public double calcuateTotalMaturity(String customerID){
        Customer customer = customerRepo.get(customerID);
        if (customer == null){
            return 0;
        }
        return customer.getPolicies().values().stream()
                .mapToDouble(Policy::calculateMaturityAmount)
                .sum();
    }

    // Display

    public void printCustomerDetails(String customerId) {
        Customer customer = customerRepo.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found: " + customerId);
            return;
        }

        System.out.println("Customer: " + customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        System.out.println("Age: " + customer.getAge());
        System.out.println("Policies:");

        customer.getPolicies().values().forEach(policy -> {
            System.out.println("  Policy ID: " + policy.getPolicyId() +
                    ", Type: " + policy.getClass().getSimpleName() +
                    ", Premium: " + policy.getPremiumAmount() +
                    ", Term: " + policy.getTermYears() +
                    ", Maturity: " + policy.calculateMaturityAmount());
        });
    }

}