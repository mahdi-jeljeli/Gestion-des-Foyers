package com.example.foyer.Interfaces;


import com.example.foyer.DTO.AddsDto.AddReservationDto;
import com.example.foyer.Entitys.Reservation;

import java.util.List;

public interface ReservationServices {
    Reservation addReservation(AddReservationDto newReservation);
    Reservation updateReservation(Integer idReservation, AddReservationDto updatedReservation);
    void deleteReservation(Integer idReservation);
    void softDeleteReservation(Integer idReservation);
    void reactivaterReservation(Integer idReservation);
    Reservation getReservationById(Integer idReservation);
    List<Reservation> findAll();
}
