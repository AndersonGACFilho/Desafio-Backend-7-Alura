package com.alura.desafiobackend.Homes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alura.desafiobackend.Entities.Depoimento;
import com.alura.desafiobackend.Repositories.DepoimentosRepository;

@RestController
public class DepoimentoHome {
  
  @Autowired
  DepoimentosRepository depoimentosRepository;

  @GetMapping(value="/depoimentos-home")
  @ResponseBody
  public List<Depoimento> getDepoimentosHome() {
    List<Depoimento> depoimentos;
    List<Depoimento> todosDepoimentos = depoimentosRepository.findAll();
    
    if (todosDepoimentos.size() < 3) {
      depoimentos = todosDepoimentos;
    }
    else{
      //Pegar 3 depoimentos aleatórios
      int[] numeros = new int[3];
      for (int i = 0; i < numeros.length; i++) {
        numeros[i] = (int) (Math.random() * todosDepoimentos.size());
      }

      depoimentos = List.of(todosDepoimentos.get(numeros[0]), todosDepoimentos.get(numeros[1]), todosDepoimentos.get(numeros[2]));

    }

    return depoimentos;
  }
    

}
