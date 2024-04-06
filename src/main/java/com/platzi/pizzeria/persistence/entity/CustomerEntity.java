package com.platzi.pizzeria.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @OneToOne(mappedBy = "customerEntity")
    private OrderEntity orderEntity;
}
