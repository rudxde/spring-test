package com.mhp.pvh.michael.demo.demo.outbound;

import com.mhp.pvh.michael.demo.demo.core.entities.Vehicle;
import com.mhp.pvh.michael.demo.demo.core.outbound.VehiclePersistenceProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@Service
public class HashMapVehiclePersistence implements VehiclePersistenceProvider {

    private HashMap<String, Vehicle> database = new HashMap<>();

    public void insertVehicle(Vehicle newVehicle) {
        this.database.put(newVehicle.getId(), newVehicle);
    }

    public void deleteVehicle(String vehicleId) {
        this.database.remove(vehicleId);
    }

    public void updateVehicle(String vehicleId, Vehicle updatedVehicle) {
        this.database.put(vehicleId, updatedVehicle);
    }

    public Vehicle getVehicle(String vehicleId) {
        Vehicle result = this.database.get(vehicleId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with Id " + vehicleId + " was not found!");
        }
        return result;
    }
}
