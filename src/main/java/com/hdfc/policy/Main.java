package com.hdfc.policy;

import com.hdfc.policy.factory.PolicyFactory;
import com.hdfc.policy.models.Customer;
import com.hdfc.policy.models.HealthInsurancePolicy;
import com.hdfc.policy.models.LifeInsurancePolicy;
import com.hdfc.policy.models.Policy;
import com.hdfc.policy.services.PolicyServices;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        PolicyServices service = PolicyServices.getInstance();

        // Register policy types
        PolicyFactory.register("LIFE", (id, premium, term) -> new LifeInsurancePolicy(id, premium, term));
        PolicyFactory.register("HEALTH", (id, premium, term) -> new HealthInsurancePolicy(id, premium, term));

        // Create customers
        Customer c1 = new Customer("C001", "Vamsi", 22);
        Customer c2 = new Customer("C002", "Krishna", 23);
        Customer c3 = new Customer("C003", "Ram", 21);

        service.addCustomer(c1);
        service.addCustomer(c2);
        service.addCustomer(c3);

        // Add policies
        service.addPolicy("C001", PolicyFactory.createPolicy("LIFE", "P001", 5000, 10));
        service.addPolicy("C001", PolicyFactory.createPolicy("HEALTH", "P002", 3000, 5));
        service.addPolicy("C002", PolicyFactory.createPolicy("LIFE", "P003", 7000, 15));
        service.addPolicy("C003", PolicyFactory.createPolicy("HEALTH", "P004", 2000, 7));

        System.out.println("\n--- All Customers and Policies ---");
        printAllCustomers(service);

        System.out.println("\n--- Total Maturity for C001 ---");
        double maturity = service.calcuateTotalMaturity("C001");
        System.out.println("Total Maturity Amount: " + maturity);

        System.out.println("\n--- Removing Policy P002 from C001 ---");
        service.removePolicy("C001", "P002");
        service.printCustomerDetails("C001");

        System.out.println("\n--- Removing Customer C003 ---");
        service.removeCustomer("C003");

        System.out.println("\n--- Customers Sorted by Name ---");
        TreeMap<String, Customer> sortedByName = new TreeMap<>();
        for (Customer cust : service.getAllCustomers()) {
            sortedByName.put(cust.getName(), cust);
        }
        for (Map.Entry<String, Customer> entry : sortedByName.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", ID: " + entry.getValue().getCustomerId());
        }

        System.out.println("\n--- All Policies Sorted by Premium Amount ---");
        List<Policy> allPolicies = service.getAllCustomers().stream()
                .flatMap(cust -> cust.getPolicies().values().stream())
                .sorted(Comparator.comparingDouble(Policy::getPremiumAmount))
                .collect(Collectors.toList());

        for (Policy policy : allPolicies) {
            System.out.println("Policy ID: " + policy.getPolicyId() +
                    ", Premium: " + policy.getPremiumAmount());
        }

        System.out.println("\n--- Iterate Using keySet() ---");
        Customer customer = service.getCustomer("C001");
        if (customer != null) {
            for (String key : customer.getPolicies().keySet()) {
                System.out.println("Policy ID: " + key);
            }
        }

        System.out.println("\n--- Iterate Using entrySet() ---");
        if (customer != null) {
            for (Map.Entry<String, Policy> entry : customer.getPolicies().entrySet()) {
                System.out.println("Policy ID: " + entry.getKey() +
                        ", Premium: " + entry.getValue().getPremiumAmount());
            }
        }

        System.out.println("\n--- Iterate Using forEach() ---");
        if (customer != null) {
            customer.getPolicies().forEach((id, pol) ->
                    System.out.println("Policy ID: " + id + ", Term: " + pol.getTermYears()));
        }
    }

    private static void printAllCustomers(PolicyServices service) {
        for (Customer cust : service.getAllCustomers()) {
            service.printCustomerDetails(cust.getCustomerId());
        }
    }
}





