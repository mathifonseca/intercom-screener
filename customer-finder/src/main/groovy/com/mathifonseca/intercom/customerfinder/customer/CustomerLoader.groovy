package com.mathifonseca.intercom.customerfinder.customer

import groovy.json.JsonSlurper

class CustomerLoader {

    static void loadFromFile(File file) {

        if (!file) {
            throw new IllegalArgumentException()
        } else if (!file.exists()) {
            throw new FileNotFoundException()
        }

        JsonSlurper slurper = new JsonSlurper()

        file.eachLine { line ->
            Map json
            try {
                json = slurper.parseText(line)
            } catch (JsonException) {
                println 'Invalid JSON format, ignoring line: ' + line
            }
            if (json) {
                Customer customer = Customer.fromMap(json)
                CustomerRepository.persist(customer)
            }
        }

    }

}
