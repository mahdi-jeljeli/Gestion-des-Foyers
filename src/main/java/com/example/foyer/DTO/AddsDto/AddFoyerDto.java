package com.example.foyer.DTO.AddsDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFoyerDto {
    @NotNull
    private String NameFoyer;
    @NotNull
    @Positive
    private   long CapaciteFoyer;
}
