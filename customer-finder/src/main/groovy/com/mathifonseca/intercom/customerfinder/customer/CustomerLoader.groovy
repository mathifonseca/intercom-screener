package com.mathifonseca.intercom.customerfinder.customer

import groovy.json.JsonSlurper

class CustomerLoader {

    static void loadFromFile(File file) {

        if (!file) {
            throw new IllegalArgumentException()
        } else if (!file.exists()) {
            throw new FileNotFoundException()
        }

        def slurper = new JsonSlurper()

        file.eachLine { line ->
            def json
            try {
                json = slurper.parseText(line)
            } catch (JsonException) {
                //ignored
            }
            if (json) {
                def customer = Customer.buildFromMap(json)
                CustomerRepository.persist(customer)
            }
        }

    }

}
