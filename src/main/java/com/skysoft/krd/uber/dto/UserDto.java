package com.skysoft.krd.uber.dto;

import com.skysoft.krd.uber.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.digester.Rule;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private  String name;
    private String email;
    private Set<Role> role;
}
