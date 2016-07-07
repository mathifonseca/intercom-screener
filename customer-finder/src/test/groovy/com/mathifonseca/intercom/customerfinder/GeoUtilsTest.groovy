package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.geo.GeoUtils
import com.mathifonseca.intercom.customerfinder.geo.Point
import spock.lang.Specification

class GeoUtilsTest extends Specification {

    def "test distance between same point"() {
        given: "a point"
            Point point1 = new Point(latitude: 1.1, longitude: -1.1)
        when: "distance method is called"
            double result = GeoUtils.distance(point1, point1)
        then: "zero is returned"
            result == 0d
    }

    def "test distance between poles"() {
        given: "the north and south pole points"
            Point north = new Point(latitude: 90, longitude: 0)
            Point south = new Point(latitude: -90, longitude: 0)
        when: "distance method is called"
            double result = GeoUtils.distance(north, south)
        then: "the correct distance is returned"
            result.toInteger() == 20020
    }

    def "test distance between my house and Intercom offices"() {
        given: "the two points"
            Point home = new Point(latitude: -34.8796215, longitude: -56.1490744)
            Point intercom = new Point(latitude: 53.3381985, longitude: -6.2592576)
        when: "distance method is called"
            double result = GeoUtils.distance(home, intercom)
        then: "the correct distance is returned"
            result.toInteger() == 10925
    }

}
