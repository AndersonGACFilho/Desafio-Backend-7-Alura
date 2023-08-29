package com.alura.desafiobackend.Entities;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Depoimento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Nullable
  private Long id;
  @Column(nullable = false)
  private String nome;
  @Column(nullable = false)
  private String depoimento;
  @Column(nullable = false)
  private String foto;

  // Construtores


  public Depoimento( String nome, String depoimento, String foto) {
    this.nome = nome;
    this.depoimento = depoimento;
    this.foto = foto;
  }

  public Depoimento() {
  }

  // Atributos Getters e Setters
  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return this.nome;
  }

  public void setDepoimento(String depoimento) {
    this.depoimento = depoimento;
  }

  public String getDepoimento() {
    return this.depoimento;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getFoto() {
    return this.foto;
  }

  // toString
  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", nome='" + getNome() + "'" +
      ", depoimento='" + getDepoimento() + "'" +
      ", foto='" + getFoto() + "'" +
      "}";
  }
}
