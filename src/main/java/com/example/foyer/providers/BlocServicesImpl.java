package com.example.foyer.providers;

import com.example.foyer.DTO.AddsDto.AddBlocDto;
import com.example.foyer.DTO.AddsDto.AddChambreForBlocDto;
import com.example.foyer.DTO.UpdatesDto.UpdateBlocDto;
import com.example.foyer.Entitys.Bloc;
import com.example.foyer.Entitys.Chambre;
import com.example.foyer.Entitys.Foyer;
import com.example.foyer.Interfaces.BlocServices;
import com.example.foyer.Repository.BlocRepository;
import com.example.foyer.Repository.ChambreRepository;
import com.example.foyer.Repository.FoyerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class BlocServicesImpl implements BlocServices {

    private BlocRepository blocRepository;
    private ChambreRepository chambreRepository;
    private  final FoyerRepository foyerRepository ;
    @Autowired
    public BlocServicesImpl(
            BlocRepository blocRepository
            , ChambreRepository chambreRepository,
            FoyerRepository foyerRepository
    ){
        this.blocRepository  = blocRepository ;
        this.chambreRepository = chambreRepository;
        this.foyerRepository = foyerRepository ;
    }
    @Override
    public Bloc addBloc(AddBlocDto newBloc) {
        // Vérifiez d'abord si le foyer existe
        Optional<Foyer> optionalFoyer = foyerRepository.findById(newBloc.getIdFoyer());
        if (optionalFoyer.isPresent()) {
            Foyer foyer = optionalFoyer.get();

            Bloc bloc = new Bloc();
            bloc.setNameBloc(newBloc.getNameBloc());
            bloc.setCapaciteBloc(newBloc.getCapaciteBloc());
            bloc.setFoyer(foyer); // Associez le bloc au foyer récupéré

            // Créez et associez les chambres avec le bloc
            Set<Chambre> chambres = createChambresForBloc(newBloc.getChambre(), bloc);
            bloc.setChambre(chambres);

            // Enregistrez le bloc avec les chambres associées
            return blocRepository.save(bloc);
        } else {
            throw new NotFoundException("Foyer not found with id: " + newBloc.getIdFoyer());
        }
    }

    private Set<Chambre> createChambresForBloc(Set<AddChambreForBlocDto> newChambres, Bloc bloc) {
        Set<Chambre> createdChambres = new HashSet<>();
        for (AddChambreForBlocDto chambreDto : newChambres) {
            Chambre chambre = new Chambre();
            chambre.setNumeroChambre(chambreDto.getNumeroChambre());
            chambre.setTypeChambre(chambreDto.getType());
            chambre.setBloc(bloc); // Associate chambre with bloc
            // Save chambre to repository
            chambre = this.chambreRepository.save(chambre);
            // Add the saved chambre to the createdChambres set
            createdChambres.add(chambre);
        }

        return createdChambres;
    }

    @Override
    public void deleteBloc(int blocId) {
        // Retrieve the bloc entity to be deleted
        Bloc bloc = blocRepository.findById(blocId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + blocId));

        // Step 2: Delete the bloc, cascade deletion will delete associated chambres
        this.blocRepository.delete(bloc);
    }
    @Override
    public Bloc updateBloc(Integer blocId, UpdateBlocDto updatedBloc) {
        // Step 1: Retrieve the existing Bloc entity from the database
        Bloc existingBloc = blocRepository.findById(blocId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + blocId));

        // Step 2: Update the existing Bloc entity with data from updatedBloc DTO
        existingBloc.setNameBloc(updatedBloc.getNameBloc());
        existingBloc.setCapaciteBloc(updatedBloc.getCapaciteBloc());

        // Step 3: Save the updated Bloc entity back to the database
        Bloc savedBloc = blocRepository.save(existingBloc);

        // Step 4: Return the updated Bloc entity
        return savedBloc;
    }


    @Override
    public void softDeleteBloc(Integer BlocId) {

    }

    @Override
    public void reactivaterBloc(Integer BlocId) {

    }

    @Override
    public Bloc getBlocById(Integer blocId) {
        return blocRepository.findById(blocId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + blocId));
    }

    @Override
    @Async
    public List<Bloc> findAll() {
        return (List<Bloc>) blocRepository.findAll();
    }
}
