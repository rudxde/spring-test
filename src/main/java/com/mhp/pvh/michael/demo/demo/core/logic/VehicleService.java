package com.mhp.pvh.michael.demo.demo.core.logic;

import com.mhp.pvh.michael.demo.demo.core.dto.CreateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.dto.UpdateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.entities.Vehicle;
import com.mhp.pvh.michael.demo.demo.core.inbound.VehicleServiceProvider;
import com.mhp.pvh.michael.demo.demo.core.outbound.VehiclePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class VehicleService implements VehicleServiceProvider {

    @Autowired
    private VehiclePersistenceProvider vehiclePersistenceProvider;

    public String createVehicle(CreateVehicleDto vehicle, String currentUserId) {
        String id = UUID.randomUUID().toString();
        Vehicle newVehicle = new Vehicle(
                id,
                vehicle.getModel(),
                vehicle.getProductionYear(),
                currentUserId);
        this.vehiclePersistenceProvider.insertVehicle(newVehicle);
        return id;
    }

    public void deleteVehicle(String vehicleId, String currentUserId) {
        Vehicle vehicle = this.getVehicle(vehicleId);
        if (!vehicle.getOwner().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the ownler is allowed to alter vehicle");
        }
        this.vehiclePersistenceProvider.deleteVehicle(vehicleId);
    }

    public Vehicle getVehicle(String vehicleId) {
        return this.vehiclePersistenceProvider.getVehicle(vehicleId);
    }

    public void updateVehicle(String vehicleId, UpdateVehicleDto vehicle, String currentUserId) {
        Vehicle existing = this.getVehicle(vehicleId);
        if (!existing.getOwner().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the ownler is allowed to alter vehicle");
        }
        Vehicle newVehicle = new Vehicle(
                existing.getId(),
                vehicle.getModel() != null ? vehicle.getModel() : existing.getModel(),
                vehicle.getProductionYear() != null ? vehicle.getProductionYear() : existing.getProductionYear(),
                vehicle.getOwner() != null ? vehicle.getOwner() : existing.getOwner());
        this.vehiclePersistenceProvider.updateVehicle(vehicleId, newVehicle);
    }
}
