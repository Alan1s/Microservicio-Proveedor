package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entities.Proveedor;

public interface Proveedor_Repository extends CrudRepository<Proveedor, Long> {
}

