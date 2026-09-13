package com.employeepayroll.util;

import com.employeepayroll.model.Employee;
import java.util.HashMap;
import java.util.Map;

public class EmployeeCache {
    private final Map<Integer, Employee> cache = new HashMap<>();

    public void put(Employee e) {
        cache.put(e.getId(), e);
    }

    public Employee get(int id) {
        return cache.get(id);
    }

    public void remove(int id) {
        cache.remove(id);
    }

    public int size() {
        return cache.size();
    }
}
