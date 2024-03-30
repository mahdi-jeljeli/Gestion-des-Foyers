package com.example.foyer.Controller;

import com.example.foyer.Commun.Log;
import com.example.foyer.DTO.AddsDto.AddEtudiantDto;
import com.example.foyer.DTO.AddsDto.AddFoyerDto;
import com.example.foyer.DTO.UpdatesDto.UpdateEtudiantDto;
import com.example.foyer.Entitys.Etudiant;
import com.example.foyer.Entitys.Foyer;
import com.example.foyer.Interfaces.EtudiantServices;
import com.example.foyer.Interfaces.FoyerServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/Foyer")
public class FoyerController {
    @Autowired
    FoyerServices foyerServices;
    private static final Logger MONITORING_LOGGER = Log.getMonitoringLogger();
    private static final Logger EXCEPTION_LOGGER = Log.getExceptionLogger();
    private static final Logger FATAL_LOGGER = Log.getFatalLogger();
    private static final Logger SQL_LOGGER = Log.getSqlLogger();
    public FoyerController ( FoyerServices foyerServices){
        this.foyerServices = foyerServices ;
    }
    @GetMapping(path = "/AllFoyer")
    public ResponseEntity<List<Foyer>> getAllFoyer() {
        try {
            List<Foyer> etudiants = (List<Foyer>) foyerServices.findAll();
            return ResponseEntity.ok().body(etudiants);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve Foyer", e);
        }
    }

    @PostMapping(path="/add")
    public ResponseEntity<Map<String, Serializable>> addFoyer(@Valid @RequestBody AddFoyerDto newFoyer) {
        try {
            Foyer res = this.foyerServices.addFoyer(newFoyer);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Foye ajouté", "id", res.getIdFoyer())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", "Une erreur est survenue lors de l'ajout du Foyer", "error", e.getMessage())
            );
        }
    }

    @DeleteMapping("DeleteFoyer/{idFoyer}")
    public ResponseEntity<String> DeleteFoyer(@PathVariable String idFoyer) {
        try {
            int id = Integer.parseInt(idFoyer);
            foyerServices.deleteFoyer(id);
            return ResponseEntity.ok("Foyer supprimé avec succès !!");
        }catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }


    @PutMapping("/{idFoyer}")
    public ResponseEntity<Foyer> updateEtudiant(
            @PathVariable String idFoyer ,
            @RequestBody AddFoyerDto updatedidFoyer) {
        try {
            int id = Integer.parseInt(idFoyer);
            Foyer updated = foyerServices.updateFoyer(id, updatedidFoyer);

            // Enregistrez un message de log pour indiquer la mise à jour réussie
            MONITORING_LOGGER.info("Foyer updated successfully: {}", updated);

            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            // Enregistrez l'exception dans le fichier de log avec le niveau ERROR
            EXCEPTION_LOGGER.error("Foyer not found: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Enregistrez l'exception dans le fichier de log avec le niveau ERROR
            EXCEPTION_LOGGER.error("Failed to update Foyer", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update Foyer", e);
        }
    }

}
