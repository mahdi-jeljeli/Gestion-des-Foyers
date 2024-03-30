package com.example.foyer.Entitys;

import com.example.foyer.Commun.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Bloc")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc extends TimeStamp implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long idBloc;
    @Column(
            name = "nomB",
            length = 25
    )
    String NameBloc;
    @Column(
            name = "CapB"
    )
    long CapaciteBloc;
    @ManyToOne
    Foyer foyer;
    @OneToMany(mappedBy = "bloc" , cascade = CascadeType.ALL)
    Set<Chambre> chambre ;

}
