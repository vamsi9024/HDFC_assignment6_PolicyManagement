package com.hdfc.policy.factory;

import com.hdfc.policy.models.Policy;

import java.util.HashMap;
import java.util.Map;

public class PolicyFactory {

    private static Map<String ,PolicyCreator> registry =  new HashMap<>();

    public static void register(String type, PolicyCreator creator) {

        registry.put(type.toLowerCase(), creator);

    }

    public static Policy createPolicy(String type, String policyId, double premium, int termYears) {

        PolicyCreator creator = registry.get(type.toLowerCase());

        if (creator == null) {
            throw new IllegalArgumentException("Unknown policy type: " + type);
        }

        return creator.create(policyId, premium, termYears);
    }
}
