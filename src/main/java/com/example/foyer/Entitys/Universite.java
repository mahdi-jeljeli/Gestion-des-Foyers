package com.example.foyer.Entitys;

import com.example.foyer.Commun.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "Universite")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite extends TimeStamp implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long idUniversite;
    @Column(
            name = "NomU",
            length = 25
    )
    String NameUniversite;
    @Column(
            name = "AdresseU"
    )
    String AdresseUniversite;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "universite")
    private Foyer foyer;
}
