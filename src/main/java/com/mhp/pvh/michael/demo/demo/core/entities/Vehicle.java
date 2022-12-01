package com.mhp.pvh.michael.demo.demo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
public class Vehicle {
    @NotBlank(message = "Id is required")
    @Getter
    private String id;
    @NotBlank(message = "Model is required")
    @Getter
    private String model;
    @Min(value = 0, message = "productionYear is required")
    @Getter
    private Integer productionYear;
    @Getter
    private String owner;
}
