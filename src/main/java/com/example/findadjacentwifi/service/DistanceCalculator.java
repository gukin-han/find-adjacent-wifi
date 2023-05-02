package com.example.findadjacentwifi.service;

// simple version
public class DistanceCalculator {

    public double distance(double lat1, double lnt1, double lat2, double lnt2) {
        double dLat = Math.abs(lat2 - lat1);
        double dLnt = Math.abs(lnt2 - lnt1);

        return Math.pow(dLat, 2) + Math.pow(dLnt, 2); // simple version
    }
}

