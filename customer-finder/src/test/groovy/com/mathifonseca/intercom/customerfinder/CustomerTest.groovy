package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.customer.Customer
import spock.lang.Specification

class CustomerTest extends Specification {

    def "test fromMap empty map"() {
        given: "an empty map"
            Map map = [:]
        when: "fromMap method is called"
            Customer.fromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test fromMap missing user_id"() {
        given: "a map without user_id"
            Map map = [ name : 'Mathias Fonseca', latitude : 1.1, longitude : 1.1 ]
        when: "fromMap method is called"
            Customer.fromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test fromMap missing name"() {
        given: "a map without user_id"
            Map map = [ user_id : 1, latitude : 1.1, longitude : 1.1 ]
        when: "fromMap method is called"
            Customer.fromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test fromMap missing latitude"() {
        given: "a map without latitude"
            Map map = [ user_id : 1, name : 'Mathias Fonseca', longitude : 1.1 ]
        when: "fromMap method is called"
            Customer.fromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test fromMap missing longitude"() {
        given: "a map without longitude"
            Map map = [ user_id : 1, name : 'Mathias Fonseca', latitude : 1.1 ]
        when: "fromMap method is called"
            Customer.fromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test fromMap"() {
        given: "a valid map"
            Map map = [ user_id : 1, name : 'Mathias Fonseca', latitude : 1.1, longitude : 1.1 ]
        when: "fromMap method is called"
            Customer customer = Customer.fromMap(map)
        then: "a Customer instance is returned"
            customer
            customer.id == map.user_id
            customer.name == map.name
            customer.location
            customer.location.latitude == map.latitude
            customer.location.longitude == map.longitude
    }

}
