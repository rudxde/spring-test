package com.mhp.pvh.michael.demo.demo.core.inbound;

import com.mhp.pvh.michael.demo.demo.core.dto.CreateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.dto.UpdateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.entities.Vehicle;

public interface VehicleServiceProvider {
    String createVehicle(CreateVehicleDto vehicle);
    void deleteVehicle(String vehicleId);

    Vehicle getVehicle(String vehicleId);
    void updateVehicle(String vehicleId, UpdateVehicleDto vehicle);
}
