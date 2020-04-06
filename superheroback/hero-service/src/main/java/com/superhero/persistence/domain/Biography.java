package com.superhero.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Biography {

    @Id
    @GeneratedValue
    @Column(name = "biography_id")
    private Long id;

    @Column(name = "full_name")
    @JsonProperty("full-name")
    private String fullName;

    @Column(name = "alter_egos")
    @JsonProperty("alter-egos")
    private String alterEgos;

    @Column(name = "place_of_birth")
    @JsonProperty( "place-of-birth")
    private String placeOfBirth;

    @Column(name = "first_appearance")
    @JsonProperty( "first-appearance")
    private String firstAppearance;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "alignment")
    private String alignment;

    @ElementCollection
    private List<String> aliases;

}
