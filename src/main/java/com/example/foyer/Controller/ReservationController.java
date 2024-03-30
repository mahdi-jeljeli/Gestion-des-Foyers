package com.example.foyer.Controller;

import com.example.foyer.DTO.AddsDto.AddBlocDto;
import com.example.foyer.DTO.AddsDto.AddReservationDto;
import com.example.foyer.DTO.UpdatesDto.UpdateEtudiantDto;
import com.example.foyer.Entitys.Bloc;
import com.example.foyer.Entitys.Etudiant;
import com.example.foyer.Entitys.Reservation;
import com.example.foyer.Interfaces.ReservationServices;
import com.example.foyer.Repository.EtudiantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(path="/Reservation")
public class ReservationController {
    @Autowired
    private final ReservationServices reservationServices;


    @Autowired // Assurez-vous que l'annotation @Autowired est utilisée pour l'injection
    public ReservationController(ReservationServices reservationServices, EtudiantRepository etudiantRepository) {
        this.reservationServices = reservationServices;
    }

    @GetMapping(path = "/AllReservation")
    public ResponseEntity<List<Reservation>> getAllReservation() {
        try {
            List<Reservation> reservations = (List<Reservation>) reservationServices.findAll();
            return ResponseEntity.ok().body(reservations);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve Etudiant", e);
        }
    }

    @PostMapping(path="/addReservation")
    public ResponseEntity<Map<String, Serializable>> addResrvation(@Valid @RequestBody AddReservationDto newReservation) {
        try {
            Reservation res = this.reservationServices.addReservation(newReservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Reservation added", "id", res.getIdResarvation())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", "Une erreur est survenue lors de l'ajout de la Reservation", "error", e.getMessage())
            );
        }
    }

    @DeleteMapping("DeleteReservation/{idReservation}")
    public ResponseEntity<String> DeleteReservation(@PathVariable String idReservation) {
        try {
            int id = Integer.parseInt(idReservation);
            reservationServices.deleteReservation(id);
            return ResponseEntity.ok("Reservation supprimé avec succès !!");
        }catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/{idReservation}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable String idReservation ,
            @RequestBody AddReservationDto updatedReservation) {
        try {
            int id = Integer.parseInt(idReservation);
            Reservation updated = reservationServices.updateReservation(id, updatedReservation);
            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update Reservation", e);
        }
    }

}


