package com.platzi.pizzeria.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza")
public class PizzaEntity {
    private Integer idPizza;
}
