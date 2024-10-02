package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Proveedor_dto;
import com.example.demo.entities.Proveedor;
import com.example.demo.repositories.Proveedor_Repository;

@Service
public class Proveedor_service {
    @Autowired
    Proveedor_Repository Proveedor_Repository;
    ModelMapper modelMapper;

    
    public Proveedor_service(Proveedor_Repository Proveedor_repository, ModelMapper modelMapper) {
        this.Proveedor_Repository = Proveedor_repository;
        this.modelMapper = modelMapper;
    }

    // Crear un nuevo Proveedor
    public Proveedor_dto crearProveedor(Proveedor_dto ProveedorDto) {
        Proveedor Proveedor = modelMapper.map(ProveedorDto, Proveedor.class); // Convertir DTO a entidad
        Proveedor nuevoProveedor = Proveedor_Repository.save(Proveedor); // Guardar en la base de datos
        return modelMapper.map(nuevoProveedor, Proveedor_dto.class); // Convertir de nuevo a DTO
    }

    // Obtener todos los Proveedors
    public List<Proveedor_dto> obtenerTodosLosProveedors() {
        List<Proveedor> Proveedors = (List<Proveedor>) Proveedor_Repository.findAll();
        return Proveedors.stream()
                .map(Proveedor -> modelMapper.map(Proveedor, Proveedor_dto.class))
                .collect(Collectors.toList());
    }

    // Obtener Proveedor por ID
    public Proveedor_dto obtenerProveedorPorId(Long id) {
        Proveedor Proveedor = Proveedor_Repository.findById(id).orElse(null); // Retorna null si no encuentra el Proveedor
        if (Proveedor != null) {
            return modelMapper.map(Proveedor, Proveedor_dto.class); // Mapea la entidad al DTO
        }
        return null; // Retorna null si no se encontró
    }
    
    // Actualizar un Proveedor existente por ID
public Proveedor_dto actualizarProveedor(Long id, Proveedor_dto ProveedorDto) {
    Optional<Proveedor> ProveedorExistente = Proveedor_Repository.findById(id);

    if (ProveedorExistente.isPresent()) {
        Proveedor Proveedor = ProveedorExistente.get();
        Proveedor.setNombre(ProveedorDto.getNombre());
        Proveedor.setEdad(ProveedorDto.getEdad());
        Proveedor.setFoto(ProveedorDto.getFoto());
        Proveedor.setDescripcion(ProveedorDto.getDescripcion());
        Proveedor ProveedorActualizado = Proveedor_Repository.save(Proveedor);
        return modelMapper.map(ProveedorActualizado, Proveedor_dto.class);
    }

    return null; // Devuelve null si no se encuentra el Proveedor
}

    // Eliminar un Proveedor por nombre (ID)
    public boolean eliminarProveedor(Long id) {
        if (Proveedor_Repository.existsById(id)) {
            Proveedor_Repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método de autenticación básico (esto se puede mejorar mucho más con Spring Security)
    public boolean autenticarProveedor(Long id, String foto) {
        Optional<Proveedor> Proveedor = Proveedor_Repository.findById(id);
        if (Proveedor.isPresent()) {
            return Proveedor.get().getFoto().equals(foto); // Solo un ejemplo sencillo
        }
        return false;
    }

}
