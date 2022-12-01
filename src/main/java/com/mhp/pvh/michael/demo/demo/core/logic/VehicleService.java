package com.mhp.pvh.michael.demo.demo.core.logic;

import com.mhp.pvh.michael.demo.demo.core.dto.CreateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.dto.UpdateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.entities.Vehicle;
import com.mhp.pvh.michael.demo.demo.core.inbound.VehicleServiceProvider;
import com.mhp.pvh.michael.demo.demo.core.outbound.VehiclePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleService implements VehicleServiceProvider {

    @Autowired
    private VehiclePersistenceProvider vehiclePersistenceProvider;

    public String createVehicle(CreateVehicleDto vehicle) {
        String id = UUID.randomUUID().toString();
        Vehicle newVehicle = new Vehicle(
                id,
                vehicle.getModel(),
                vehicle.getProductionYear(),
                vehicle.getOwner()
        );
        this.vehiclePersistenceProvider.insertVehicle(newVehicle);
        return id;
    }

    public void deleteVehicle(String vehicleId) {
        this.vehiclePersistenceProvider.deleteVehicle(vehicleId);
    }

    public Vehicle getVehicle(String vehicleId) {
        return this.vehiclePersistenceProvider.getVehicle(vehicleId);
    }

    public void updateVehicle(String vehicleId, UpdateVehicleDto vehicle) {
        Vehicle existing = this.getVehicle(vehicleId);
        Vehicle newVehicle = new Vehicle(
                existing.getId(),
                vehicle.getModel() != null ? vehicle.getModel() : existing.getModel(),
                vehicle.getProductionYear() != null ? vehicle.getProductionYear() : existing.getProductionYear(),
                vehicle.getOwner() != null ? vehicle.getOwner() : existing.getOwner()
        );
        this.vehiclePersistenceProvider.updateVehicle(vehicleId, newVehicle);
    }
}
