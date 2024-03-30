package com.example.foyer.providers;

import com.example.foyer.DTO.AddsDto.AddFoyerDto;
import com.example.foyer.DTO.AddsDto.AddUniversiteDto;
import com.example.foyer.Entitys.*;
import com.example.foyer.Interfaces.UniversiteServices;
import com.example.foyer.Repository.FoyerRepository;
import com.example.foyer.Repository.UniversiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
@Transactional
public class UnivesiteServicesImpl implements UniversiteServices {
    @Autowired
    UniversiteRepository universiteRepository ;
    FoyerRepository foyerRepository ;

public UnivesiteServicesImpl (UniversiteRepository universiteRepository ,    FoyerRepository foyerRepository ){
    this.foyerRepository = foyerRepository ;
    this.universiteRepository = universiteRepository ;
}
    @Override
    public Universite addUniversite(AddUniversiteDto newUnivesite) {
        // Step 1: Create the bloc entity
        Universite universite = new Universite();
        universite.setNameUniversite(newUnivesite.getNameUniversite());
        universite.setAdresseUniversite(newUnivesite.getAdresseUniversite());

        // Create and associate Foyer with the universite
        Foyer foyer = createFoyerForUniversite(newUnivesite.getFoyer(), universite);
        universite.setFoyer(foyer);

        return this.universiteRepository.save(universite);
    }
    private Foyer createFoyerForUniversite(AddFoyerDto newFoyer, Universite universite) {
        Foyer createdFoyer = new Foyer();
        createdFoyer.setNameFoyer(newFoyer.getNameFoyer());
        createdFoyer.setCapaciteFoyer(newFoyer.getCapaciteFoyer());
        createdFoyer.setUniversite(universite);
        foyerRepository.save(createdFoyer); // Enregistrez le foyer dans la base de données
        return createdFoyer;
    }


    @Override
    public Universite updateUniversiter(Integer idUniversite, AddUniversiteDto updatedUnivesite) {
        // Récupérer l'université à mettre à jour
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);
        if (optionalUniversite.isPresent()) {
            Universite universite = optionalUniversite.get();

            // Mettre à jour les attributs de l'université avec les nouvelles valeurs
            universite.setNameUniversite(updatedUnivesite.getNameUniversite());
            universite.setAdresseUniversite(updatedUnivesite.getAdresseUniversite());

            // Mettre à jour le foyer associé à l'université si nécessaire
            if (updatedUnivesite.getFoyer() != null) {
                Foyer foyer = universite.getFoyer();
                foyer.setNameFoyer(updatedUnivesite.getFoyer().getNameFoyer());
                foyer.setCapaciteFoyer(updatedUnivesite.getFoyer().getCapaciteFoyer());
                // Assurez-vous de mettre à jour d'autres attributs du foyer si nécessaire
            }

            // Enregistrer les modifications dans la base de données
            return universiteRepository.save(universite);
        } else {
            throw new NotFoundException("Universite not found with id: " + idUniversite);
        }
    }

    @Override
    public void deleteUniversite(Integer UniversiteId) {
        Universite universite = universiteRepository.findById(UniversiteId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + UniversiteId));

        this.universiteRepository.delete(universite);
    }

    @Override
    public void softDeleteUniversite(Integer UniversiteId) {

    }

    @Override
    public void reactivaterUniversite(Integer UniversiteId) {

    }

    @Override
    public Universite getUniversiteById(Integer v) {
        return null;
    }

    @Override
    public List<Universite> findAll() {
        return (List<Universite>) universiteRepository.findAll();
    }
}
