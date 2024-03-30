package com.example.foyer.Interfaces;

import com.example.foyer.DTO.AddsDto.AddEtudiantDto;
import com.example.foyer.DTO.UpdatesDto.UpdateEtudiantDto;
import com.example.foyer.Entitys.Etudiant;

import java.util.List;

public interface EtudiantServices {
    Etudiant addEtudiant(AddEtudiantDto newEtudiant);
    Etudiant updateEtudiant(Integer EtudiantId, UpdateEtudiantDto updatedEtudiant) throws Exception;
    void deleteEtudiant(Integer EtudiantId);
    void softDeleteEtudiant(Integer EtudiantId);
    void reactivaterEtudiant(Integer EtudiantId);
    Etudiant getEtudiantById(Integer EtudiantId);
    List<Etudiant> findAll();
}
