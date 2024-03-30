package com.example.foyer.providers;

import com.example.foyer.DTO.AddsDto.AddReservationDto;
import com.example.foyer.Entitys.Chambre;
import com.example.foyer.Entitys.Etudiant;
import com.example.foyer.Entitys.Reservation;
import com.example.foyer.Interfaces.ReservationServices;
import com.example.foyer.Repository.EtudiantRepository;
import com.example.foyer.Repository.ReservationRepository;
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
public class ReservationServicesImpl implements ReservationServices {
    @Autowired
    ReservationRepository reservationRepository;
    EtudiantRepository etudiantRepository;
    public ReservationServicesImpl (ReservationRepository reservationRepository , EtudiantRepository etudiantRepository){
        this.reservationRepository = reservationRepository ;
        this.etudiantRepository = etudiantRepository ;
    }
    @Override
    public Reservation addReservation(AddReservationDto newReservation) {
        Reservation reservation = new Reservation();
        Chambre chambre = new Chambre();

        chambre.setIdChambre(newReservation.getIdChambre());
        reservation.setAnneeUniversitaire(newReservation.getAnneeUniversitaire());
        reservation.setEstValide(newReservation.getEstValide());
        reservation.setChambre(chambre);

        Set<Etudiant> etudiants = new HashSet<>();
        for (Integer idEtudiant : newReservation.getIdEtudiants()) {
            Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);
            if (optionalEtudiant.isPresent()) {
                Etudiant etudiant = optionalEtudiant.get();
                etudiant.getReservations().add(reservation); // Ajouter la réservation à l'étudiant
                etudiants.add(etudiant);
            } else {
                throw (new NotFoundException("Etudiant not found with id: " + idEtudiant));
            }
        }
        reservation.setEtudiant(etudiants);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Integer idReservation, AddReservationDto updatedReservation) {
        // Récupérer la réservation à mettre à jour
        Optional<Reservation> optionalReservation = reservationRepository.findById(idReservation);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Mettre à jour les attributs de la réservation avec les nouvelles valeurs
            reservation.setAnneeUniversitaire(updatedReservation.getAnneeUniversitaire());
            reservation.setEstValide(updatedReservation.getEstValide());
            // Assurez-vous de mettre à jour d'autres attributs si nécessaire

            // Mettre à jour la chambre si nécessaire
            if (updatedReservation.getIdChambre() != null) {
                Chambre chambre = new Chambre();
                chambre.setIdChambre(updatedReservation.getIdChambre());
                reservation.setChambre(chambre);
            }

            // Mettre à jour les étudiants associés à la réservation
            Set<Etudiant> etudiants = new HashSet<>();
            for (Integer idEtudiant : updatedReservation.getIdEtudiants()) {
                Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);
                if (optionalEtudiant.isPresent()) {
                    etudiants.add(optionalEtudiant.get());
                } else {
                    throw new NotFoundException("Etudiant not found with id: " + idEtudiant);
                }
            }
            reservation.setEtudiant(etudiants);

            // Enregistrer les modifications dans la base de données
            return reservationRepository.save(reservation);
        } else {
            throw new NotFoundException("Reservation not found with id: " + idReservation);
        }
    }

    @Override
    public void deleteReservation(Integer idReservation) {
        Reservation Reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new NotFoundException("Bloc not found with id: " + idReservation));

        this.reservationRepository.delete(Reservation);
    }

    @Override
    public void softDeleteReservation(Integer ReservationId) {

    }

    @Override
    public void reactivaterReservation(Integer ReservationId) {

    }

    @Override
    public Reservation getReservationById(Integer ReservationId) {
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        return (List<Reservation>) reservationRepository.findAll();
    }
}
