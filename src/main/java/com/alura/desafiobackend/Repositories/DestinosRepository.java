package com.alura.desafiobackend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.alura.desafiobackend.Entities.Destino;

public interface DestinosRepository extends CrudRepository<Destino, Long> {

  public Optional<Destino> findById(long id);

  public List<Destino> findAll();

  public List<Destino> findAllByNome(String nome);

  public List<Destino> findAllByPreco(Number preco);

  public Optional<Destino> findByNome(String nome);

  public Optional<Destino> findByPreco(Number preco);

}
