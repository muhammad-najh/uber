package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.clinets.OSRMClient;
import com.skysoft.krd.uber.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceServiceOSRMImpl implements DistanceService {
    private final OSRMClient osrmClient;
    @Override
    public double calculateDistance(Point src, Point dest) {
        return osrmClient.getDistanceFromLatLongOSRM(src,dest);
    }
}
