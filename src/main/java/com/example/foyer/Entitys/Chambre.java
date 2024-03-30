package com.example.foyer.Entitys;

import com.example.foyer.Commun.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Chambre")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chambre extends TimeStamp implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer idChambre;
    @Column(
            name = "NumCh",
            unique = true
    )
    long NumeroChambre;
    @Column(
            name = "TypeCh"
    )
    TypeChambre TypeChambre;
    @ManyToOne
    Bloc bloc ;
    @OneToMany(mappedBy = "chambre" , cascade = CascadeType.ALL)
    Set<Reservation> reservation;
}
