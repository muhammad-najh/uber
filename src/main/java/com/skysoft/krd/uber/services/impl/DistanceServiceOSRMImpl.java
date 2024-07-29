package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {

        // call 3th party api OSRM
        return 0;
    }
}
