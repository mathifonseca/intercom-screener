package com.mathifonseca.intercom.customerfinder.customer

import com.mathifonseca.intercom.customerfinder.geo.Point

class CustomerRepository {

    private final Map<Long, Customer> customers = [:]

    private static CustomerRepository instance = null

    private CustomerRepository() { }

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

    static List<Customer> findAllByLocationNearPoint(Point center, double radius) {
        return getInstance().customers.values()
            .findAll { it.location.isWithinKms(radius, center) }
            .sort { it.id }
    }

    static void clear() {
        getInstance().customers.clear()
    }

}
