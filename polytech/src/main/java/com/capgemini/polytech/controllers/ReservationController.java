package com.capgemini.polytech.controllers;

import com.capgemini.polytech.dto.ReservationDto;
import com.capgemini.polytech.dto.ReservationIdsDto;
import com.capgemini.polytech.models.ReservationId;
import com.capgemini.polytech.services.ReservationService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservations/")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("all")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("id")
    public ResponseEntity<ReservationDto> getReservationById(@RequestBody ReservationId id) {
        ReservationDto reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    // Endpoint pour créer une nouvelle réservation
    @PostMapping("create")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDTO) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    @PostMapping("create/ids")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationIdsDto reservationIdsDTO) {
        ReservationDto reservationDTO = reservationService.convertReservationIdsDTOtoReservationDTO(reservationIdsDTO);
        ReservationDto createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    // Endpoint to update an existing reservation
    @PutMapping("update")
    public ResponseEntity<ReservationDto> updateReservation(@RequestBody ReservationIdsDto reservationIdsDTO) {
        ReservationDto reservationDTO = reservationService.convertReservationIdsDTOtoReservationDTO(reservationIdsDTO);
        ReservationDto updatedReservation = reservationService.updateReservation(reservationDTO);
        return ResponseEntity.ok(updatedReservation);
    }

    // Endpoint to delete a reservation
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteReservation(@RequestBody ReservationId id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

}
