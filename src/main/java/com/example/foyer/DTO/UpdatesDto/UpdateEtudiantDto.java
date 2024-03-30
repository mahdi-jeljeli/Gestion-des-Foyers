package com.example.foyer.DTO.UpdatesDto;

import com.example.foyer.DTO.AddsDto.AddReservationDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UpdateEtudiantDto {
    private String nameEt;
    private String prenomEt;
    @Positive
    private long cin;
    private String ecole;
    private Date dateNaissance;
    private Set<AddReservationDto> reservations;
}
