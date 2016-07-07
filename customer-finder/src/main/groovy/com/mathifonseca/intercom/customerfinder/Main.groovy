package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.config.Config
import com.mathifonseca.intercom.customerfinder.customer.Customer
import com.mathifonseca.intercom.customerfinder.customer.CustomerLoader
import com.mathifonseca.intercom.customerfinder.customer.CustomerRepository

class Main {

    static final String CONFIG_PATH = '/config/config.properties'

    static void main(args) {

        Config config = new Config()

        config.loadConfig(CONFIG_PATH)

        CustomerLoader.loadFromFile(config.customersFile)

        List<Customer> customersNearIntercom =
                CustomerRepository.findAllByLocationNearPoint(config.nearLocation, config.nearRadius)

        println "Customers within ${config.nearRadius} kms from Intercom office:"

        customersNearIntercom.each { customer ->
            println "\t${customer.id} - ${customer.name}"
        }

    }

}
