package com.mhp.pvh.michael.demo.demo.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@AllArgsConstructor
public class CreateVehicleDto {
    @NotBlank(message = "Model is required")
    @Getter
    private String model;
    @Min(value = 0, message = "productionYear is required")
    @Getter
    private Integer productionYear;
    @Getter
    private String owner;
}
