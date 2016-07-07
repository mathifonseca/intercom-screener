package com.mathifonseca.intercom.customerfinder.customer

import com.mathifonseca.intercom.customerfinder.geo.Point

class CustomerRepository {

    private Map<Long, Customer> customers = new HashMap<>()

    private static CustomerRepository instance = null

    private CustomerRepository() {}

    private static CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository()
        }
        return instance
    }

    static void persist(Customer customer) {
        if (!getInstance().customers.containsKey(customer.id)) {
            getInstance().customers.put(customer.id, customer)
        }
    }

    static Customer findById(Long id) {
        return getInstance().customers.get(id)
    }

    static List<Customer> findAll() {
        return getInstance().customers.values().toList()
    }

    static void clear() {
        getInstance().customers.clear()
    }

}