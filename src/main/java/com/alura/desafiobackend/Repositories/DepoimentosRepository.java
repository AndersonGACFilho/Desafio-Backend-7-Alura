package com.alura.desafiobackend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.alura.desafiobackend.Entities.Depoimento;

public interface DepoimentosRepository extends CrudRepository<Depoimento, Long> {
  
  public Optional<Depoimento> findById(long id);

  public List<Depoimento> findAll();

  public List<Depoimento> findAllByNome(String nome);

  public Optional<Depoimento> findByDepoimento(String depoimento);
}
