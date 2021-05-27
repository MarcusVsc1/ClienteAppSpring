package com.ex.clienter.repository;

import java.util.List;

import com.ex.clienter.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    
    List<Cidade> findByNomeCidade(String item);

    List<Cidade> findByEstado(String item);
    
}
