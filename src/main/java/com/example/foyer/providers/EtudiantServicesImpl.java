package com.example.foyer.providers;

import com.example.foyer.DTO.AddsDto.AddEtudiantDto;
import com.example.foyer.DTO.AddsDto.AddReservationDto;
import com.example.foyer.DTO.UpdatesDto.UpdateEtudiantDto;
import com.example.foyer.Entitys.Bloc;
import com.example.foyer.Entitys.Chambre;
import com.example.foyer.Entitys.Etudiant;
import com.example.foyer.Entitys.Reservation;
import com.example.foyer.Interfaces.EtudiantServices;
import com.example.foyer.Repository.EtudiantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;

@Service
@Transactional
public class EtudiantServicesImpl implements EtudiantServices {

    @Autowired
    EtudiantRepository etudiantRepository;
    public EtudiantServicesImpl (   EtudiantRepository etudiantRepository){
        this.etudiantRepository = etudiantRepository;
    }
    @Override
    public Etudiant addEtudiant(AddEtudiantDto newEtudiant) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNameEt(newEtudiant.getNameEt());
        etudiant.setPrenomEt(newEtudiant.getPrenomEt());
        etudiant.setCin(newEtudiant.getCin());
        etudiant.setEcole(newEtudiant.getEcole());
        etudiant.setDateNaissance(newEtudiant.getDateNaissance());

        // Création des réservations associées à l'étudiant
        if (newEtudiant.getReservations() != null) {
            for (AddReservationDto reservationDto : newEtudiant.getReservations()) {
                Reservation reservation = new Reservation();
                // Initialisez les propriétés de la réservation en fonction du DTO
                reservation.setAnneeUniversitaire(reservationDto.getAnneeUniversitaire());
                reservation.setEstValide(reservationDto.getEstValide());
                // Associez la chambre et les étudiants à la réservation
                Chambre chambre = new Chambre();
                chambre.setIdChambre(reservationDto.getIdChambre());
                reservation.setChambre(chambre);
                reservation.setEtudiant(new HashSet<>(Collections.singletonList(etudiant)));
                // Ajoutez la réservation à l'étudiant
                etudiant.getReservations().add(reservation);
            }
        }

        return this.etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Integer etudiantId, UpdateEtudiantDto updatedEtudiant) throws Exception {
        Etudiant existingEtudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new NotFoundException("Etudiant not found with id: " + etudiantId));

        // Mettre à jour les attributs de l'étudiant avec les nouvelles valeurs
        existingEtudiant.setNameEt(updatedEtudiant.getNameEt());
        existingEtudiant.setPrenomEt(updatedEtudiant.getPrenomEt());
        existingEtudiant.setCin(updatedEtudiant.getCin());
        existingEtudiant.setEcole(updatedEtudiant.getEcole());
        existingEtudiant.setDateNaissance(updatedEtudiant.getDateNaissance());

        // Mettre à jour les réservations existantes de l'étudiant
        if (updatedEtudiant.getReservations() != null) {
            Set<Reservation> updatedReservations = new HashSet<>();
            for (AddReservationDto reservationDto : updatedEtudiant.getReservations()) {
                // Vérifie si la réservation existe déjà
                Optional<Reservation> optionalReservation = existingEtudiant.getReservations()
                        .stream()
                        .filter(reservation -> Objects.equals(reservation.getIdResarvation(), reservationDto.getIdReservation()))
                        .findFirst();

                if (optionalReservation.isPresent()) {
                    Reservation existingReservation = optionalReservation.get();
                    // Mettre à jour les attributs de la réservation existante
                    existingReservation.setAnneeUniversitaire(reservationDto.getAnneeUniversitaire());
                    existingReservation.setEstValide(reservationDto.getEstValide());
                    // Assurez-vous de mettre à jour d'autres attributs de la réservation si nécessaire
                    updatedReservations.add(existingReservation);
                } else {
                    throw new Exception("Une erreur est survenue lors de l'opération",new Throwable());

                }
            }
            // Mettez à jour la liste des réservations de l'étudiant avec les réservations mises à jour
            existingEtudiant.setReservations(updatedReservations);
        }

        // Enregistrez les modifications dans la base de données
        return etudiantRepository.save(existingEtudiant);
    }

    @Override
    public void deleteEtudiant(Integer EtudiantId) {
        Etudiant etudiant = etudiantRepository.findById(EtudiantId)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + EtudiantId));

        this.etudiantRepository.delete(etudiant);
    }

    @Override
    public void softDeleteEtudiant(Integer EtudiantId) {

    }

    @Override
    public void reactivaterEtudiant(Integer EtudiantId) {

    }

    @Override
    public Etudiant getEtudiantById(Integer EtudiantId) {
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        return (List<Etudiant>) etudiantRepository.findAll();
    }
}
