package com.mathifonseca.intercom.customerfinder.geo

class Point {

    double latitude
    double longitude

    void setLatitude(double lat) {
        if (lat > 90 || lat < -90) {
            throw new IllegalArgumentException()
        }
        this.latitude = lat
    }

    void setLongitude(double lng) {
        if (lng > 180 || lng < -180) {
            throw new IllegalArgumentException()
        }
        this.longitude = lng
    }

    boolean isWithinKms(double kms, Point other) {
        if (kms < 0) {
            throw new IllegalArgumentException()
        }
        return GeoUtils.distance(this, other) <= kms
    }

    static Point fromString(String coordinates) {
        if (!(coordinates ==~ /-?\d+(\.\d+)?,-?\d+(\.\d+)?/)) {
            throw new IllegalFormatException()
        }
        String[] parts = coordinates.split(',')
        return new Point(latitude : parts[0].toDouble(), longitude : parts[1].toDouble())
    }

    @Override
    String toString() {
        return "$latitude,$longitude"
    }

}
