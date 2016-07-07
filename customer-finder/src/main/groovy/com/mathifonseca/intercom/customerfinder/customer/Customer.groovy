package com.mathifonseca.intercom.customerfinder.customer

import com.mathifonseca.intercom.customerfinder.geo.Point

class Customer {

    Long id
    String name
    Point location

    static Customer buildFromMap(Map json) {

        if (!json || !json.user_id || !json.user_id.toString().isLong() ||
                !json.name || json.latitude == null || json.longitude == null) {
            throw new IllegalArgumentException()
        }

        Customer customer = new Customer(
            id: json.user_id.toLong(),
            name: json.name,
            location: new Point(
                latitude: json.latitude.toDouble(),
                longitude: json.longitude.toDouble()
            )
        )

        return customer

    }
    
}
