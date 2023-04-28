package com.example.findadjacentwifi;

public class DistanceCalculator {

    public final double EARTH_RADIUS = 6371; // Earth's radius in km

    public double distance(double lat1, double lnt1, double lat2, double lnt2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLnt = Math.toRadians(lnt2 - lnt1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLnt / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return EARTH_RADIUS * c;
    }
}

