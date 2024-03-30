package com.example.foyer.DTO.AddsDto;

import com.example.foyer.Entitys.TypeChambre;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddChambreForBlocDto {
    @NotNull
    @Positive
    private  long NumeroChambre;
    @NotNull
    private TypeChambre Type;

}
