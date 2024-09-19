package com.skysoft.krd.uber.configs;


import com.skysoft.krd.uber.dto.PointDto;
import com.skysoft.krd.uber.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper mapper=new ModelMapper();

        //converting PointDto to Point
        mapper.typeMap(PointDto.class, Point.class).setConverter(mappingContext -> {
            PointDto pointDto=mappingContext.getSource();
            return GeometryUtil.createPoint(pointDto);

        });

        //converting Point to PointDto
        mapper.typeMap(Point.class,PointDto.class).setConverter(mappingContext -> {
            Point point=mappingContext.getSource();
            double x=point.getX();
            double y=point.getY();
            return new PointDto(new double[]{x,y});
        });

        return mapper;
    }
}
