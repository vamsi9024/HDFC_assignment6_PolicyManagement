package com.hdfc.policy.models;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Customer{

    private String customerId;
    private String name;
    private int age;

    private Map<String, Policy> policies;
    {
        policies = new HashMap<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(Map<String, Policy> policies) {
        this.policies = policies;
    }

    public Customer(String customerId, String name, int age) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", policies=" + policies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(customerId, customer.customerId) && Objects.equals(name, customer.name) && Objects.equals(policies, customer.policies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, age, policies);
    }

}
