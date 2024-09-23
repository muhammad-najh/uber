package com.skysoft.krd.uber.clinets;

import org.locationtech.jts.geom.Point;

import java.awt.geom.Point2D;

public interface OSRMClient {

    double getDistanceFromLatLongOSRM(Point source, Point destination);

}