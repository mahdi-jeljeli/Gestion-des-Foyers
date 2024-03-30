package com.example.foyer.Interfaces;


import com.example.foyer.DTO.AddsDto.AddUniversiteDto;
import com.example.foyer.Entitys.Universite;

import java.util.List;

public interface UniversiteServices {
    Universite addUniversite(AddUniversiteDto newUnivesite);
    Universite updateUniversiter(Integer UniversiteId, AddUniversiteDto updatedUnivesite);
    void deleteUniversite(Integer UniversiteId);
    void softDeleteUniversite(Integer UniversiteId);
    void reactivaterUniversite(Integer UniversiteId);
    Universite getUniversiteById(Integer v);
    List<Universite> findAll();
}
