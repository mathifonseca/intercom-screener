package com.mathifonseca.intercom.customerfinder.geo

class GeoUtils {

    private static final double EARTH_RADIUS = 6372.8

    static double distance(Point point1, Point point2) {
        return haversine(point1.latitude, point1.longitude, point2.latitude, point2.longitude)
    }

    private static double haversine(double lat1, double lon1, double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1)
        double dLon = Math.toRadians(lon2 - lon1)

        double radLat1 = Math.toRadians(lat1)
        double radLat2 = Math.toRadians(lat2)

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(radLat1) * Math.cos(radLat2)
        double c = 2 * Math.asin(Math.sqrt(a))

        return EARTH_RADIUS * c

    }

}
