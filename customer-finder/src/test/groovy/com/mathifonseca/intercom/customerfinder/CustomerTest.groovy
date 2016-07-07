package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.customer.Customer
import spock.lang.Specification

class CustomerTest extends Specification {

    def "test buildFromMap empty map"() {
        given: "an empty map"
            def map = [:]
        when: "buildFromMap method is called"
            Customer.buildFromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test buildFromMap missing user_id"() {
        given: "a map without user_id"
            def map = [ name : 'Mathias Fonseca', latitude : 1.1, longitude : 1.1 ]
        when: "buildFromMap method is called"
            Customer.buildFromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test buildFromMap missing name"() {
        given: "a map without user_id"
            def map = [ user_id : 1, latitude : 1.1, longitude : 1.1 ]
        when: "buildFromMap method is called"
            Customer.buildFromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test buildFromMap missing latitude"() {
        given: "a map without latitude"
            def map = [ user_id : 1, name : 'Mathias Fonseca', longitude : 1.1 ]
        when: "buildFromMap method is called"
            Customer.buildFromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test buildFromMap missing longitude"() {
        given: "a map without longitude"
            def map = [ user_id : 1, name : 'Mathias Fonseca', latitude : 1.1 ]
        when: "buildFromMap method is called"
            Customer.buildFromMap(map)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test buildFromMap"() {
        given: "a valid map"
            def map = [ user_id : 1, name : 'Mathias Fonseca', latitude : 1.1, longitude : 1.1 ]
        when: "buildFromMap method is called"
            Customer customer = Customer.buildFromMap(map)
        then: "a Customer instance is returned"
            customer
            customer.id == map.user_id
            customer.name == map.name
            customer.location
            customer.location.latitude == map.latitude
            customer.location.longitude == map.longitude
    }

}
