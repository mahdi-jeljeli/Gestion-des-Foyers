package com.example.foyer.Entitys;

import com.example.foyer.Commun.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Foyer")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer extends TimeStamp implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer idFoyer;
    @Column(
            name = "NomF",
            length = 25
    )
    String NameFoyer;
    @Column(
            name = "CapF"
    )
    long CapaciteFoyer;
    @OneToOne()
    Universite universite ;
    @OneToMany(mappedBy = "foyer")
    Set<Bloc> bloc;

}
