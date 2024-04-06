package com.platzi.pizzeria.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(OrderItemId.class)
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(name = "quantity", nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Double quantity;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;

    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", updatable = false, insertable = false)
    private PizzaEntity pizzaEntity;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", updatable = false, insertable = false)
    private OrderEntity orderEntity;
}
