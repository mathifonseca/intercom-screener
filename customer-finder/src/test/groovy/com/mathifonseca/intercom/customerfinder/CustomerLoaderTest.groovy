package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.customer.CustomerLoader
import com.mathifonseca.intercom.customerfinder.customer.CustomerRepository
import spock.lang.Specification

class CustomerLoaderTest extends Specification {

    def "test loadFromFile no file"() {
        given: "a null file"
            File file = null
        when: "loadFromFile method is called"
            CustomerLoader.loadFromFile(file)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test loadFromFile non-existent file"() {
        given: "a non-existent file"
            File file = new File('/nothing.txt')
        when: "loadFromFile method is called"
            CustomerLoader.loadFromFile(file)
        then: "a FileNotFoundException is thrown"
            thrown(FileNotFoundException)
    }

    def "test loadFromFile empty file"() {
        given: "an empty file"
            File file = new File(this.class.getResource('/data/empty.txt').path)
        when: "loadFromFile method is called"
            CustomerLoader.loadFromFile(file)
        then: "no customers loaded"
            CustomerRepository.findAll().isEmpty()
    }

    def "test loadFromFile invalid json"() {
        given: "a file with an invalid json"
            File file = new File(this.class.getResource('/data/invalid-json.txt').path)
        when: "loadFromFile method is called"
            CustomerLoader.loadFromFile(file)
        then: "no customers loaded"
            CustomerRepository.findAll().isEmpty()
    }

    def "test loadFromFile one valid and one invalid"() {
        given: "a file with one valid and one invalid customer"
            File file = new File(this.class.getResource('/data/one-valid-one-invalid.txt').path)
        when: "loadFromFile method is called"
            CustomerLoader.loadFromFile(file)
        then: "only one customer is loaded"
            CustomerRepository.findAll().size() == 1
    }

    def "test loadFromFile valid json"() {
        given: "a file with 32 valid customers"
            File file = new File(this.class.getResource('/data/customers.txt').path)
        when: "loadFromFile method is called"
            CustomerLoader.loadFromFile(file)
        then: "all 32 customers are loaded"
            CustomerRepository.findAll().size() == 32
    }

}
