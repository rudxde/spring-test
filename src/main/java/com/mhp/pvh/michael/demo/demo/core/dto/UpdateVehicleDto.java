package com.mhp.pvh.michael.demo.demo.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class UpdateVehicleDto {
    @Getter
    private String model;
    @Getter
    private Integer productionYear;
    @Getter
    private String owner;
}
