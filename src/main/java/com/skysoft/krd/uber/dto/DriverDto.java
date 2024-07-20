package com.skysoft.krd.uber.dto;

import com.skysoft.krd.uber.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private UserDto user;
    private Double rating;

}
