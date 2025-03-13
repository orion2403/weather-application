package com.orion.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "sessions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Session {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "empires_at")
    private BigDecimal expiresAt;
}
