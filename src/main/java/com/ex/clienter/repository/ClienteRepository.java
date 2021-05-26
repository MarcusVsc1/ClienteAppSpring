package com.ex.clienter.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.ex.clienter.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    List<Cliente> findByNomeCliente(String itemBusca);

    


}
