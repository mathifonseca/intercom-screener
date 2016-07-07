package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.config.Config
import com.mathifonseca.intercom.customerfinder.config.InvalidConfigException
import com.mathifonseca.intercom.customerfinder.geo.Point
import spock.lang.Specification

class ConfigTest extends Specification {

    Config config

    void setup() {
        config = new Config()
    }

    def "test loadConfig without path"() {
        given: "a null or empty path"
            String path = null
        when: "loadConfig method is called"
            config.loadConfig(path)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test loadConfig with invalid path"() {
        given: "an invalid path"
            String path = 'aninvalidpath'
        when: "loadConfig method is called"
            config.loadConfig(path)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test loadConfig with missing configuration"() {
        when: "loadConfig method is called"
            config.loadConfig(path)
        then: "a InvalidConfigException is thrown"
            Exception ex = thrown(InvalidConfigException)
            ex.message == msg
        where:
            path                               | msg
            '/config/empty.properties'         | 'empty'
            '/config/missing1.properties'      | 'customers.file'
            '/config/missing2.properties'      | 'near.radius'
            '/config/missing3.properties'      | 'near.location'
    }

    def "test loadConfig with valid configuration"() {
        given: "a valid config file"
            String path = '/config/config.properties'
        when: "loadConfig method is called"
            config.loadConfig(path)
        then: "configuration is loaded"
            config.customersFile != null
            config.nearRadius != null
            config.nearLocation != null
    }

    def "test loadCustomerFile with invalid customer path"() {
        when: "loadCustomerFile method is called"
            config.loadCustomerFile(prop)
        then: "a InvalidConfigException is thrown"
            Exception ex = thrown(InvalidConfigException)
            ex.message == msg
        where:
            prop                | msg
            ''                  | 'customers.file'
            '/customers.xls'    | 'customers.file'
    }

    def "test loadCustomerFile with valid customer path"() {
        given: "a valid customer path"
            String prop = '/data/customers.txt'
        when: "loadCustomerFile method is called"
            config.loadCustomerFile(prop)
        then: "config is loaded"
            config.customersFile != null
    }

    def "test loadNearRadius with invalid value"() {
        when: "loadNearRadius method is called"
            config.loadNearRadius(prop)
        then: "a InvalidConfigException is thrown"
            Exception ex = thrown(InvalidConfigException)
            ex.message == msg
        where:
            prop            | msg
            ''              | 'near.radius'
            'notadouble'    | 'near.radius'
            '-1'            | 'near.radius'
    }

    def "test loadNearRadius with valid value"() {
        when: "loadNearRadius method is called"
            config.loadNearRadius(prop)
        then: "config is loaded"
            config.nearRadius == expected
        where:
            prop                        | expected
            '0'                         | 0
            '100'                       | 100
            Double.MAX_VALUE.toString() | Double.MAX_VALUE
    }

    def "test loadNearLocation with invalid point"() {
        given:
            Point.metaClass.fromString = { String coords ->
                throw new IllegalFormatException()
            }
        when: "loadNearLocation method is called"
            config.loadNearLocation(prop)
        then: "a InvalidConfigException is thrown"
            Exception ex = thrown(InvalidConfigException)
            ex.message == msg
        where:
            prop            | msg
            ''              | 'near.location'
            'notapoint'     | 'near.location'
            '1.1'            | 'near.location'
            '1.1'            | 'near.location'
    }

    def "test loadNearLocation with valid value"() {
        when: "loadNearLocation method is called"
            config.loadNearLocation(prop)
        then: "config is loaded"
            config.nearLocation != null
            config.nearLocation.toString() == expected
        where:
            prop                | expected
            '1,1'               | '1.0,1.0'
            '-1,-1'             | '-1.0,-1.0'
            '0,0'               | '0.0,0.0'
            '53.1212,53.1212'   | '53.1212,53.1212'
            '0.1,-0.1'          | '0.1,-0.1'
    }

}
