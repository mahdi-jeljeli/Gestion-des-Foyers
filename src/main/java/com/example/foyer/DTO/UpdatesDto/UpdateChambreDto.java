package com.example.foyer.DTO.UpdatesDto;

import com.example.foyer.Entitys.TypeChambre;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChambreDto {
    @NotNull
    @Positive
    private  long NumeroChambre;
    @NotNull
    TypeChambre Type;
}
