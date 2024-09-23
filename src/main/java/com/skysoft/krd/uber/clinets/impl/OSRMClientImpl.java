package com.skysoft.krd.uber.clinets.impl;

import com.skysoft.krd.uber.clinets.OSRMClient;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.awt.geom.Point2D;
import java.util.List;

@Service
public class OSRMClientImpl implements OSRMClient {

    @Qualifier("getOSRMClient")
    private final RestClient osrmRestClient;

    public OSRMClientImpl(RestClient osrmRestClient) {
        this.osrmRestClient = osrmRestClient;
    }

    @Override
    public double getDistanceFromLatLongOSRM(Point source, Point destination ) {
        String uri=source.getX()+","+source.getY()+";"+destination.getX()+","+destination.getY();

        try {
            OSRMResponseDto responseDto = osrmRestClient.get()
                    .uri(uri)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {

                        //log staff

                    })
                    .body(new ParameterizedTypeReference<>() {
                    });


            return responseDto.getRoutes().get(0).getDistance() / 1000;
        }catch (Exception e) {
            //log staff
            throw new RuntimeException("Error getting distance from OSRM", e);
        }
    }

    @Data
    static class OSRMResponseDto{
        List<OSRMRoute> routes;
    }

    @Data
    static class OSRMRoute{

        private Double distance;

    }
}
