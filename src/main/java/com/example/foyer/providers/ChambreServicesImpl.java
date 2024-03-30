package com.example.foyer.providers;

import com.example.foyer.DTO.AddsDto.AddChambreForBlocDto;
import com.example.foyer.DTO.AddsDto.AddNewChambreDto;
import com.example.foyer.DTO.UpdatesDto.UpdateChambreDto;
import com.example.foyer.Entitys.Bloc;
import com.example.foyer.Entitys.Chambre;
import com.example.foyer.Interfaces.ChambreServices;
import com.example.foyer.Repository.BlocRepository;
import com.example.foyer.Repository.ChambreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChambreServicesImpl implements ChambreServices {
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;
    @Autowired
    public ChambreServicesImpl(ChambreRepository chambreRepository,  BlocRepository blocRepository){
        this.chambreRepository  = chambreRepository ;
        this.blocRepository = blocRepository;
    }
    @Override
    public Chambre addChambre(AddNewChambreDto newChambre) {
        // Vérifiez d'abord si le bloc existe
        Optional<Bloc> optionalBloc = blocRepository.findById(newChambre.getBlocid());
        if (optionalBloc.isPresent()) {
            Bloc bloc = optionalBloc.get(); // Récupérez le bloc existant

            Chambre chambre = new Chambre();
            chambre.setNumeroChambre(newChambre.getNumeroChambre());
            chambre.setTypeChambre(newChambre.getType());
            chambre.setBloc(bloc); // Associez la chambre au bloc récupéré

            // Enregistrez la chambre avec le bloc associé
            return chambreRepository.save(chambre);
        } else {
            throw new NotFoundException("Bloc not found with id: " + newChambre.getBlocid());
        }
    }


    @Override
    public Chambre updateChambre(Integer ChambreId, UpdateChambreDto updatedChambre) {
        Chambre existingChambre = chambreRepository.findById(ChambreId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + ChambreId));

        existingChambre.setNumeroChambre(updatedChambre.getNumeroChambre());
        existingChambre.setTypeChambre(updatedChambre.getType());

        Chambre savedChambre = chambreRepository.save(existingChambre);

        return savedChambre;
    }

    @Override
    public void deleteChambre(Integer ChambreId) {
        Chambre chambre = chambreRepository.findById(ChambreId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + ChambreId));

        this.chambreRepository.delete(chambre);
    }

    @Override
    public void softDeleteChambre(Integer ChambreId) {

    }

    @Override
    public void reactivaterChambre(Integer ChambreId) {

    }

    @Override
    public Chambre getChambreById(Integer ChambreId) {
        return null;
    }

    @Override
    public List<Chambre> findAll() {
        return (List<Chambre>) chambreRepository.findAll();
    }
}
