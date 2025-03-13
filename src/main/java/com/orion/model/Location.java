package com.orion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
