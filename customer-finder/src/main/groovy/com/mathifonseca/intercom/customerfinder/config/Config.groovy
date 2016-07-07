package com.mathifonseca.intercom.customerfinder.config

import com.mathifonseca.intercom.customerfinder.geo.Point

class Config {

    private static final String CUSTOMERS_FILE = 'customers.file'
    private static final String NEAR_RADIUS = 'near.radius'
    private static final String NEAR_LOCATION = 'near.location'

    File customersFile
    double nearRadius
    Point nearLocation

    void loadConfig(String configPath) {

        if (!configPath) {
            throw new IllegalArgumentException()
        }

        def configFile = this.class.getResource(configPath)

        if (!configFile) {
            throw new IllegalArgumentException()
        }

        def props = new Properties()

        new File(configFile.path).withInputStream { props.load(it) }

        if (props.isEmpty()) {
            throw new InvalidConfigException('empty')
        }

        loadCustomerFile(props.get(CUSTOMERS_FILE).toString())
        loadNearRadius(props.get(NEAR_RADIUS).toString())
        loadNearLocation(props.get(NEAR_LOCATION).toString())

    }

    void loadCustomerFile(String prop) {

        if (!prop) {
            throw new InvalidConfigException(CUSTOMERS_FILE)
        }

        def fileUrl = this.class.getResource(prop)

        if (!fileUrl) {
            throw new InvalidConfigException(CUSTOMERS_FILE)
        }

        customersFile = new File(fileUrl.path)

    }

    void loadNearRadius(String prop) {

        if (!prop) {
            throw new InvalidConfigException(NEAR_RADIUS)
        }

        if (!prop.isDouble() || prop.toDouble() < 0) {
            throw new InvalidConfigException(NEAR_RADIUS)
        }

        nearRadius = prop.toDouble()

    }

    void loadNearLocation(String prop) {

        if (!prop) {
            throw new InvalidConfigException(NEAR_LOCATION)
        }

        def point

        try {
            point = Point.fromString(prop)
        } catch (IllegalFormatException) {
            throw new InvalidConfigException(NEAR_LOCATION)
        }

        nearLocation = point

    }

}