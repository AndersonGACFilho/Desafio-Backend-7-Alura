package com.alura.desafiobackend.Entities;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Destino {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Nullable
  private Long id;

  @Column(nullable = false)
  private String foto1;

  @Column(nullable = false)
  private String foto2;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private Number preco;

  @Column(nullable = false)
  private String meta;

  @Column(nullable = true)
  private String textoDescritivo;

  public Destino( String foto1, String foto2, String nome, Number preco, String meta, String textoDescritivo) {
    this.foto1 = foto1;
    this.foto2 = foto2;
    this.nome = nome;
    this.preco = preco;
    this.meta = meta;
    this.textoDescritivo = textoDescritivo;
  }

  public Destino() {
  }

  public Long getId() {
    return this.id;
  }

  public String getNome() {
    return this.nome;
  }

  public String getFoto1() {
    return this.foto1;
  }

  public String getFoto2() {
    return this.foto2;
  }

  public Number getPreco() {
    return this.preco;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setFoto1(String foto) {
    this.foto1 = foto;
  }

  public void setFoto2(String foto) {
    this.foto2 = foto;
  }

  public void setPreco(Number preco) {
    this.preco = preco;
  }

  public String getMeta() {
    return this.meta;
  }

  public void setMeta(String meta) {
    this.meta = meta;
  }

  public String getTextoDescritivo() {
    return this.textoDescritivo;
  }

  public void setTextoDescritivo(String textoDescritivo) {
    this.textoDescritivo = textoDescritivo;
  }
  
  //ToString
  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", foto1='" + getFoto1() + "'" +
      ", foto2='" + getFoto2() + "'" +
      ", nome='" + getNome() + "'" +
      ", preco='" + getPreco() + "'" +
      ", meta='" + getMeta() + "'" +
      ", textoDescritivo='" + getTextoDescritivo() + "'" +
      "}";
  }



}
