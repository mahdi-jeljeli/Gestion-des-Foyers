package com.example.foyer.DTO.AddsDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
public class AddReservationDto {
    private Date anneeUniversitaire;
    private Boolean estValide;
    private Integer idChambre;
    private Set<Integer> idEtudiants;
    private Integer idReservation;
}
