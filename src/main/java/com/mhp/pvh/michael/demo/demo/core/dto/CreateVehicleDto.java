package com.mhp.pvh.michael.demo.demo.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateVehicleDto {

    CreateVehicleDto() {
    }

    @NotBlank(message = "Model is required")
    @Getter
    private String model;
    @Min(value = 0, message = "productionYear is required")
    @Getter
    private Integer productionYear;
}
