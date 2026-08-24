package com.baozi.store.repository;

import com.baozi.store.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos adicionais personalizados podem ser adicionados aqui
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}