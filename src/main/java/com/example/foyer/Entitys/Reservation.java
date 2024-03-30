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
@Table(name = "Reservation")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation extends TimeStamp implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long idResarvation;
    @Column
    Date AnneeUniversitaire ;
    @Column
    Boolean EstValide ;
    @ManyToOne
    Chambre chambre;
    @ManyToMany(mappedBy = "reservations")
    Set<Etudiant> etudiant = new HashSet<>();

}
