package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import com.example.demo.dto.Proveedor_dto;
import com.example.demo.service.Proveedor_service;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/proveedor")
public class Proveedor_Controller {
    Proveedor_service Proveedor_service;

    @Autowired
    public Proveedor_Controller(Proveedor_service Proveedor_service) {
        this.Proveedor_service = Proveedor_service;
    }

    // Crear un nuevo Proveedor
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor_dto> crearProveedor(@RequestBody Proveedor_dto ProveedorDto) {
        Proveedor_dto nuevoProveedor = Proveedor_service.crearProveedor(ProveedorDto);
        return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
    }

    // Obtener todos los Proveedors
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proveedor_dto>> obtenerTodosLosProveedors() {
        List<Proveedor_dto> Proveedors = Proveedor_service.obtenerTodosLosProveedors();
        return new ResponseEntity<>(Proveedors, HttpStatus.OK);
    }

    // Obtener Proveedor por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor_dto> obtenerProveedorPorId(@PathVariable("id") Long id) {
        Proveedor_dto Proveedor = Proveedor_service.obtenerProveedorPorId(id);

        if (Proveedor != null) {
            return new ResponseEntity<>(Proveedor, HttpStatus.OK); // Retorna el Proveedor con estado 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 si no encuentra el Proveedor
        }
    }


    // Actualizar un Proveedor existente por ID
@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Proveedor_dto> actualizarProveedor(@PathVariable("id") Long id, @RequestBody Proveedor_dto ProveedorDto) {
    Proveedor_dto ProveedorActualizado = Proveedor_service.actualizarProveedor(id, ProveedorDto);

    if (ProveedorActualizado != null) {
        return new ResponseEntity<>(ProveedorActualizado, HttpStatus.OK);
     } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un Proveedor por ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable("id") Long id) {
        boolean eliminado = Proveedor_service.eliminarProveedor(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
