package com.superhero.persistence.domain;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aliases {
    @Id
    @GeneratedValue
    @Column(name = "alias_id")
    private Long id;

    @NonNull
    @Column(name = "value")
    private String value;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="biography_id")
    private Biography biography;

}
