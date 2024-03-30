package com.example.foyer.Interfaces;


import com.example.foyer.DTO.AddsDto.AddFoyerDto;
import com.example.foyer.Entitys.Foyer;

import java.util.List;

public interface FoyerServices {
    Foyer addFoyer(AddFoyerDto newFoyer);
    Foyer updateFoyer(Integer FoyerId, AddFoyerDto updatedFoyer);
    void deleteFoyer(Integer FoyerId);
    void softDeleteFoyer(Integer FoyerId);
    void reactivaterFoyer(Integer FoyerId);
    Foyer getFoyerById(Integer FoyerId);
    List<Foyer> findAll();
}
