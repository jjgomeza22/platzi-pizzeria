package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhoneNumber(@Param("phone") String phone);
}
