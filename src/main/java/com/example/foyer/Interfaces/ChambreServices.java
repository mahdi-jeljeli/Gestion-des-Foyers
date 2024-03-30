package com.example.foyer.Interfaces;

import com.example.foyer.DTO.AddsDto.AddChambreForBlocDto;
import com.example.foyer.DTO.AddsDto.AddNewChambreDto;
import com.example.foyer.DTO.UpdatesDto.UpdateChambreDto;
import com.example.foyer.Entitys.Chambre;

import java.util.List;

public interface ChambreServices {
    Chambre addChambre(AddNewChambreDto newChambre);
    Chambre updateChambre(Integer ChambreId, UpdateChambreDto updatedChambre);
    void deleteChambre(Integer ChambreId);
    void softDeleteChambre(Integer ChambreId);
    void reactivaterChambre(Integer ChambreId);
    Chambre getChambreById(Integer ChambreId);
    List<Chambre> findAll();
}
