package com.example.foyer.Entitys;

import com.example.foyer.Commun.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Etudiant")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant extends TimeStamp implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer idEt;
    @Column(
            name = "NomEt",
            length = 25
    )
    String NameEt;
    @Column(
            name = "PrenomEt",
            length = 25
    )
    String PrenomEt;
    @Column(
            unique = true
    )
    long cin ;
    @Column
    String Ecole;
    @Column(
            name = "DateNaiss"
    )
    Date DateNaissance;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Reservation> reservations = new HashSet<>();
}
