package com.example.foyer.Repository;

import com.example.foyer.Entitys.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}