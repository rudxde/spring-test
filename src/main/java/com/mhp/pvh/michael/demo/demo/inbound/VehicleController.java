package com.mhp.pvh.michael.demo.demo.inbound;

import com.mhp.pvh.michael.demo.demo.auth.dto.EncodedSessionToken;
import com.mhp.pvh.michael.demo.demo.auth.entities.UserAccount;
import com.mhp.pvh.michael.demo.demo.auth.inbound.AuthServiceProvider;
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

    @Autowired
    private AuthServiceProvider authService;

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicle(
            @PathVariable String vehicleId) {
        Vehicle result = this.vehicleServiceProvider.getVehicle(vehicleId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<String> createVehicle(
            @RequestHeader("authorization") String authHeader,
            @RequestBody CreateVehicleDto vehicle) {
        UserAccount account = this.authService.getAccountFromToken(new EncodedSessionToken(authHeader));
        String id = this.vehicleServiceProvider.createVehicle(vehicle, account.getUserId());
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity deleteVehicle(
            @RequestHeader("authorization") String authHeader,
            @PathVariable String vehicleId) {
        UserAccount account = this.authService.getAccountFromToken(new EncodedSessionToken(authHeader));
        this.vehicleServiceProvider.deleteVehicle(vehicleId, account.getUserId());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{vehicleId}")
    public ResponseEntity<String> updateVehicle(
            @RequestHeader("authorization") String authHeader,
            @PathVariable String vehicleId,
            @RequestBody UpdateVehicleDto vehicle) {
        UserAccount account = this.authService.getAccountFromToken(new EncodedSessionToken(authHeader));
        this.vehicleServiceProvider.updateVehicle(vehicleId, vehicle, account.getUserId());
        return ResponseEntity.ok().build();
    }
}
