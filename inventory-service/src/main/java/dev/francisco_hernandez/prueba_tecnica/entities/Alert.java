package dev.francisco_hernandez.prueba_tecnica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer currentStock;

    @Column(nullable = false)
    private Integer minStock;

    @Column(nullable = false)
    private String severity;
}
