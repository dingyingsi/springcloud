package com.dys.spring.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private Date birthOfDay;
}
