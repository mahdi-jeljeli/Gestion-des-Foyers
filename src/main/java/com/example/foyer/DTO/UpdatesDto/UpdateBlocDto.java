package com.example.foyer.DTO.UpdatesDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBlocDto {

    @NotEmpty
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 255)
    private String NameBloc;
    @NotNull
    @Positive
    private  long CapaciteBloc;
}
