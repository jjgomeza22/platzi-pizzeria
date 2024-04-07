package com.platzi.pizzeria.persistence.entity;

import com.platzi.pizzeria.persistence.audit.AuditPizzaListener;
import com.platzi.pizzeria.persistence.audit.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class,AuditPizzaListener.class})
@Table(name = "pizza")
public class PizzaEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private Integer idPizza;

    @Column(name = "name", nullable = false, length = 30, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "price", columnDefinition = "DECIMAL(5,2)")
    private Double price;

    @Column(name = "vegetarian", columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(name = "vegan", columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(name = "available", columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

    @Override
    public String toString() {
        return "PizzaEntity{" +
               "idPizza=" + idPizza +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", vegetarian=" + vegetarian +
               ", vegan=" + vegan +
               ", available=" + available +
               '}';
    }
}
