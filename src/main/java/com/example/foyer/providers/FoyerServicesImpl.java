package com.example.foyer.providers;

import com.example.foyer.DTO.AddsDto.AddFoyerDto;
import com.example.foyer.Entitys.Etudiant;
import com.example.foyer.Entitys.Foyer;
import com.example.foyer.Interfaces.FoyerServices;
import com.example.foyer.Repository.FoyerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
@Service
@Transactional
public class FoyerServicesImpl implements FoyerServices {

    @Autowired
    FoyerRepository foyerRepository;
    public  FoyerServicesImpl (FoyerRepository foyerRepository){
        this.foyerRepository = foyerRepository;
    }
    @Override
    public Foyer addFoyer(AddFoyerDto newFoyer) {
        Foyer foyer = new Foyer();
        foyer.setNameFoyer(newFoyer.getNameFoyer());
        foyer.setCapaciteFoyer(newFoyer.getCapaciteFoyer());
        return this.foyerRepository.save(foyer);
    }

    @Override
    public Foyer updateFoyer(Integer FoyerId, AddFoyerDto updatedFoyer) {
        Foyer existingFoyer = foyerRepository.findById(FoyerId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + FoyerId));

        existingFoyer.setNameFoyer(updatedFoyer.getNameFoyer());
        existingFoyer.setCapaciteFoyer(updatedFoyer.getCapaciteFoyer());
        Foyer savedFoyer = foyerRepository.save(existingFoyer);

        return savedFoyer;
    }

    @Override
    public void deleteFoyer(Integer FoyerId) {
        Foyer foyer = foyerRepository.findById(FoyerId)
                .orElseThrow(() -> new NotFoundException("Foyer not found with id: " + FoyerId));

        this.foyerRepository.delete(foyer);
    }

    @Override
    public void softDeleteFoyer(Integer FoyerId) {

    }

    @Override
    public void reactivaterFoyer(Integer FoyerId) {

    }

    @Override
    public Foyer getFoyerById(Integer FoyerId) {
        return null;
    }

    @Override
    public List<Foyer> findAll() {
        return (List<Foyer>) foyerRepository.findAll();
    }
}
