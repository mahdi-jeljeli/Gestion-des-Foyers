package com.example.foyer.Interfaces;

import com.example.foyer.DTO.AddsDto.AddBlocDto;
import com.example.foyer.DTO.UpdatesDto.UpdateBlocDto;
import com.example.foyer.Entitys.Bloc;

import java.util.List;

public interface BlocServices {
    Bloc addBloc(AddBlocDto newBloc);
    Bloc updateBloc(Integer userId, UpdateBlocDto updatedBloc);
     void deleteBloc(int BlocId);
    void softDeleteBloc(Integer BlocId);
    void reactivaterBloc(Integer BlocId);
    Bloc getBlocById(Integer BlocId);
    List<Bloc> findAll();
}
