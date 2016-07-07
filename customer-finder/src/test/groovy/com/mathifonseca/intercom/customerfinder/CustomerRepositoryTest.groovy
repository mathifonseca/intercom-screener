package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.customer.Customer
import com.mathifonseca.intercom.customerfinder.customer.CustomerRepository
import com.mathifonseca.intercom.customerfinder.geo.Point
import spock.lang.Specification

class CustomerRepositoryTest extends Specification {

    void setup() {
        CustomerRepository.clear()
    }

    def "test persist"() {
        given: "a Customer"
            Customer customer = new Customer(id : 1)
            int size = CustomerRepository.findAll().size()
        when: "persist method is called"
            CustomerRepository.persist(customer)
        then: "Customer is persisted"
            CustomerRepository.findAll().size() == size + 1
    }

    def "test persist existing"() {
        given: "a Customer and an existing customer"
            CustomerRepository.persist(new Customer(id : 1))
            Customer customer = new Customer(id : 1)
            int size = CustomerRepository.findAll().size()
        when: "persist method is called"
            CustomerRepository.persist(customer)
        then: "Customer is not persisted"
            CustomerRepository.findAll().size() == size
    }

    def "test findById"() {
        given: "an existing user and an id"
            CustomerRepository.persist(new Customer(id : 1))
            Long id = 1
        when: "findById method is called"
            Customer found = CustomerRepository.findById(id)
        then: "Customer is returned"
            found
            found.id == id
    }

    def "test findById non-existent"() {
        given: "an existing user and an id"
            CustomerRepository.persist(new Customer(id : 1))
            Long id = 2
        when: "findById method is called"
            Customer found = CustomerRepository.findById(id)
        then: "Customer is not found"
            !found
    }

    def "test findAll"() {
        given: "10 existing users"
            10.times { it ->
                CustomerRepository.persist(new Customer(id : it))
            }
        when: "findAll method is called"
            List<Customer> list = CustomerRepository.findAll()
        then: "list with 10 customers is returned"
            list
            list.size() == 10
    }

    def "test findAllByLocationNearPoint"() {
        given: "some initial data"
            List points = [
                new Point(latitude : -34.904394, longitude : -56.1914907),
                new Point(latitude : -34.8951546, longitude : -56.1703243),
                new Point(latitude : -34.877529, longitude : -56.1443874),
                new Point(latitude : -32.3660957, longitude : -54.1610219)
            ]
            points.eachWithIndex { point, i ->
                CustomerRepository.persist(new Customer(id : i, name : "cust$i", location : point))
            }
            Point center = new Point(latitude : -34.905714, longitude : -56.1898166)
            double radius = 5
        when: "findAllByLocationNearPoint method is called"
            List<Customer> list = CustomerRepository.findAllByLocationNearPoint(center, radius)
        then: "list with matching customers is returned"
            list
            list.size() == 2
    }

}
