package com.mhp.pvh.michael.demo.demo.inbound;

import com.mhp.pvh.michael.demo.demo.core.dto.CreateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.dto.UpdateVehicleDto;
import com.mhp.pvh.michael.demo.demo.core.entities.Vehicle;
import com.mhp.pvh.michael.demo.demo.core.inbound.VehicleServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("vehicle")
public class VehicleController {

    @Autowired
    private VehicleServiceProvider vehicleServiceProvider;

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String vehicleId) {
        Vehicle result = this.vehicleServiceProvider.getVehicle(vehicleId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<String> createVehicle(@RequestBody CreateVehicleDto vehicle) {
        String id = this.vehicleServiceProvider.createVehicle(vehicle);
        return  ResponseEntity.ok(id);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity deleteVehicle(@PathVariable String vehicleId) {
        this.vehicleServiceProvider.deleteVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{vehicleId}")
    public ResponseEntity<String> updateVehicle (
            @PathVariable String vehicleId,
            @RequestBody UpdateVehicleDto vehicle
    ) {
        this.vehicleServiceProvider.updateVehicle(vehicleId, vehicle);
        return  ResponseEntity.ok().build();
    }
}
