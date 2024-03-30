package com.example.foyer.Controller;

import com.example.foyer.DTO.AddsDto.AddChambreForBlocDto;
import com.example.foyer.DTO.AddsDto.AddNewChambreDto;
import com.example.foyer.DTO.UpdatesDto.UpdateChambreDto;
import com.example.foyer.Entitys.Chambre;
import com.example.foyer.Interfaces.ChambreServices;
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
@RequestMapping(path="/Chambres")
public class ChambreController {
    @Autowired
    private ChambreServices chambreServices;


    @GetMapping(path = "/AllBlocs")
    public ResponseEntity<List<Chambre>> getAllBlocs() {
        try {
            List<Chambre> chanbre = (List<Chambre>) chambreServices.findAll();
            return ResponseEntity.ok().body(chanbre);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve Chambre", e);
        }
    }
    @PostMapping(path="/add")
    public ResponseEntity<Map<String, Serializable>> addNewUser(@Valid @RequestBody AddNewChambreDto newChambre) {
        try {
            Chambre res = this.chambreServices.addChambre(newChambre);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Chambre added", "id", res.getIdChambre())
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chambre not found",e);
        }
    }

    @DeleteMapping("DeleteChambre/{ChambrecId}")
    public ResponseEntity<String> DeleteChambre(@PathVariable String ChambrecId) {
        try {
            int id = Integer.parseInt(ChambrecId);
            chambreServices.deleteChambre(id);
            return ResponseEntity.ok("Chambre supprimé avec succès !!");
        }catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/{ChambrecId}")
    public ResponseEntity<Chambre> updateChambre(
            @PathVariable String ChambrecId ,
                @RequestBody UpdateChambreDto updatedChambre) {
        try {
            int id = Integer.parseInt(ChambrecId);
            Chambre updated = chambreServices.updateChambre(id, updatedChambre);
            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update Chambre", e);
        }
    }
}
