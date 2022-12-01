package com.mhp.pvh.michael.demo.demo.core.outbound;

import com.mhp.pvh.michael.demo.demo.core.entities.Vehicle;

public interface VehiclePersistenceProvider {
    void insertVehicle(Vehicle newVehicle);
    void deleteVehicle(String vehicleId);
    void updateVehicle(String vehicleId, Vehicle updatedVehicle);
    Vehicle getVehicle(String vehicleId);
}
