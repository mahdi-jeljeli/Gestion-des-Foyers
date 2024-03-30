package com.example.foyer.Controller;

import com.example.foyer.DTO.AddsDto.AddBlocDto;
import com.example.foyer.DTO.AddsDto.AddEtudiantDto;
import com.example.foyer.DTO.UpdatesDto.UpdateChambreDto;
import com.example.foyer.DTO.UpdatesDto.UpdateEtudiantDto;
import com.example.foyer.Entitys.Bloc;
import com.example.foyer.Entitys.Chambre;
import com.example.foyer.Entitys.Etudiant;
import com.example.foyer.Interfaces.EtudiantServices;
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
@RequestMapping(path="/Etudiant")
public class EtudiantController {
    @Autowired
    EtudiantServices etudiantServices;
    public EtudiantController (   EtudiantServices etudiantServices){
        this.etudiantServices = etudiantServices ;
    }
    @GetMapping(path = "/AllEtutiants")
    public ResponseEntity<List<Etudiant>> getAllEtutiants() {
        try {
            List<Etudiant> etudiants = (List<Etudiant>) etudiantServices.findAll();
            return ResponseEntity.ok().body(etudiants);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve Etudiant", e);
        }
    }
    @PostMapping(path="/add")
    public ResponseEntity<Map<String, Serializable>> addEtudiant(@Valid @RequestBody AddEtudiantDto newEtudiant) {
        try {
            Etudiant res = this.etudiantServices.addEtudiant(newEtudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Etudiant ajouté", "id", res.getIdEt())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", "Une erreur est survenue lors de l'ajout de l'étudiant", "error", e.getMessage())
            );
        }
    }

    @DeleteMapping("DeleteEtudiant/{idEtudiant}")
    public ResponseEntity<String> DeleteEtudiant(@PathVariable String idEtudiant) {
        try {
            int id = Integer.parseInt(idEtudiant);
            etudiantServices.deleteEtudiant(id);
            return ResponseEntity.ok("Etudiant supprimé avec succès !!");
        }catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/{idEtudiant}")
    public ResponseEntity<Etudiant> updateEtudiant(
            @PathVariable String idEtudiant ,
            @RequestBody UpdateEtudiantDto updatedEtudiant) {
        try {
            int id = Integer.parseInt(idEtudiant);
            Etudiant updated = etudiantServices.updateEtudiant(id, updatedEtudiant);
            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update Etudiant", e);
        }
    }

}
