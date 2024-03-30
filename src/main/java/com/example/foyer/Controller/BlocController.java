package com.example.foyer.Controller;
import com.example.foyer.DTO.AddsDto.AddBlocDto;
import com.example.foyer.DTO.UpdatesDto.UpdateBlocDto;
import com.example.foyer.Entitys.Bloc;
import com.example.foyer.Interfaces.BlocServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.webjars.NotFoundException;

@RestController
@RequestMapping(path="/Bloc")
public class BlocController {
    @Autowired
    private BlocServices blocServices;

    @GetMapping(path = "/AllBlocs")
    public ResponseEntity<List<Bloc>> getAllBlocs() {
        try {
            List<Bloc> blocs = (List<Bloc>) blocServices.findAll();
            return ResponseEntity.ok().body(blocs);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve blocs", e);
        }
    }


    @PostMapping(path="/add")
    public ResponseEntity<Map<String, Serializable>> addBloc(@Valid @RequestBody AddBlocDto newBloc) {
        try {
            Bloc res = this.blocServices.addBloc(newBloc);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Bloc added", "id", res.getIdBloc())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", "Une erreur est survenue lors de l'ajout de l'étudiant", "error", e.getMessage())
            );
        }
    }


    @DeleteMapping("DeleteBloc/{BlocId}")
    public ResponseEntity<String> softDeleteUser(@PathVariable String BlocId) {
        try {
            int id = Integer.parseInt(BlocId);
            blocServices.deleteBloc(id);
            return ResponseEntity.ok("Bloc supprimé avec succès !!");
        }catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }


    @PutMapping("/{blocId}")
    public ResponseEntity<Bloc> updateBloc(
            @PathVariable String blocId ,
            @RequestBody UpdateBlocDto updatedBloc) {
        try {
            int id = Integer.parseInt(blocId);
            Bloc updated = blocServices.updateBloc(id, updatedBloc);
            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update bloc", e);
        }
    }
}
