package com.example.foyer.DTO.AddsDto;

import com.example.foyer.Entitys.Foyer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUniversiteDto {
  private   String NameUniversite;
  private String AdresseUniversite;
  private AddFoyerDto foyer ;
}
