package com.example.foyer.Controller;

import com.example.foyer.DTO.AddsDto.AddReservationDto;
import com.example.foyer.DTO.AddsDto.AddUniversiteDto;
import com.example.foyer.Entitys.Reservation;
import com.example.foyer.Entitys.Universite;
import com.example.foyer.Interfaces.ReservationServices;
import com.example.foyer.Interfaces.UniversiteServices;
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
@RequestMapping(path="/Universite")
public class UniversiteController {
    @Autowired
    private final UniversiteServices universiteServices;
    @Autowired
    public UniversiteController(UniversiteServices universiteServices) {
        this.universiteServices = universiteServices;
    }

    @GetMapping(path = "/AllUniversite")
    public ResponseEntity<List<Universite>> getAllUniversite() {
        try {
            List<Universite> universite = (List<Universite>) universiteServices.findAll();
            return ResponseEntity.ok().body(universite);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve Universite", e);
        }
    }

    @PostMapping(path="/adduniversite")
    public ResponseEntity<Map<String, Serializable>> addUniversite(@Valid @RequestBody AddUniversiteDto newUniversite) {
        try {
            Universite res = this.universiteServices.addUniversite(newUniversite);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Universite added", "id", res.getIdUniversite())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", "Une erreur est survenue lors de l'ajout de l'universite", "error", e.getMessage())
            );
        }
    }

    @DeleteMapping("DeleteUniversite/{idUniversite}")
    public ResponseEntity<String> DeleteReservation(@PathVariable String idUniversite) {
        try {
            int id = Integer.parseInt(idUniversite);
            universiteServices.deleteUniversite(id);
            return ResponseEntity.ok("Universite supprimé avec succès !!");
        }catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/{idUniversite}")
    public ResponseEntity<Universite> updateUniversite(
            @PathVariable String idReservation ,
            @RequestBody AddUniversiteDto updatedUniversite) {
        try {
            int id = Integer.parseInt(idReservation);
            Universite updated = universiteServices.updateUniversiter(id, updatedUniversite);
            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update Universite", e);
        }
    }

}
