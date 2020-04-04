package com.superhero.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Powerstats {

    @Id
    @GeneratedValue
    @Column(name = "powerstats_id")
    private Long id;

    @Column(name = "intelligence")
    private Integer intelligence;

    @Column(name = "strength")
    private Integer strength;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "durability")
    private Integer durability;

    @Column(name = "power")
    private Integer power;

    @Column(name = "combat")
    private Integer combat;
}
