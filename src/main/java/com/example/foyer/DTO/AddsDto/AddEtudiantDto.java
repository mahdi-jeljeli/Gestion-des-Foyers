package com.example.foyer.DTO.AddsDto;

import java.util.Date;
import java.util.Set;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEtudiantDto {
    @NotNull
    private String nameEt;
    private String prenomEt;
    @NotNull
    @Positive
    private long cin;
    @NotNull
    private String ecole;
    @NotNull
    private Date dateNaissance;
    private Set<AddReservationDto> reservations;
}
