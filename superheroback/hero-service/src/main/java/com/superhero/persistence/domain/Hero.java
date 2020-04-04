package com.superhero.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hero {

    @Id
    @GeneratedValue
    @Column(name = "hero_id")
    private Long id;

    @Column(name = "external_id",unique = true)
    private Long externalId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "biography_id", referencedColumnName = "biography_id")
    private Biography biography;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "powerstats_id", referencedColumnName = "powerstats_id")
    private Powerstats powerstats;


}
