package com.mathifonseca.intercom.customerfinder

import com.mathifonseca.intercom.customerfinder.geo.GeoUtils
import com.mathifonseca.intercom.customerfinder.geo.Point
import spock.lang.Specification

class PointTest extends Specification {

    void "test setLatitude invalid value"() {
        given:
            Point point = new Point()
        when:
            point.latitude = val
        then:
            thrown(IllegalArgumentException)
        where:
            val     | _
            -91     | _
            91      | _
            2000    | _
            -2000   | _
    }

    void "test setLatitude valid value"() {
        given:
            Point point = new Point()
        when:
            point.latitude = val
        then:
            point.latitude == val
        where:
            val    | _
            90     | _
            0      | _
            -90    | _
            53.0   | _
    }

    void "test setLongitude invalid value"() {
        given:
            Point point = new Point()
        when:
            point.longitude = val
        then:
            thrown(IllegalArgumentException)
        where:
            val     | _
            -181    | _
            181     | _
            2000    | _
            -2000   | _
    }

    void "test setLongitude valid value"() {
        given:
            Point point = new Point()
        when:
            point.longitude = val
        then:
            point.longitude == val
        where:
            val     | _
            -180    | _
            180     | _
            0       | _
            153.0   | _
    }

    def "test withinKms negative kms"() {
        given:
            Point point1 = new Point(latitude: 1.1, longitude: -1.1)
            Point point2 = new Point(latitude: 1.0, longitude: -1.0)
        when: "isWithinKms method is called"
            point1.isWithinKms(-1, point2)
        then: "an IllegalArgumentException is thrown"
            thrown(IllegalArgumentException)
    }

    def "test withinKms"() {
        given:
            GeoUtils.metaClass.static.distance = { Point p1, Point p2 ->
                return 50
            }
            Point point1 = new Point(latitude: 1.1, longitude: -1.1)
            Point point2 = new Point(latitude: 1.0, longitude: -1.0)
        when: "isWithinKms method is called"
            boolean result = point1.isWithinKms(within, point2)
        then: "a boolean is returned"
            result == expected
        where:
            within           | expected
            0                | false
            49               | false
            50               | true
            100              | true
            Double.MAX_VALUE | true
    }

}