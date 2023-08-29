package com.alura.desafiobackend.Homes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alura.desafiobackend.Entities.Destino;
import com.alura.desafiobackend.Repositories.DestinosRepository;

@RestController
public class DestinosHome {

  @Autowired
  DestinosRepository destinosRepository;

  @GetMapping(value="/destinos")
  @ResponseBody
  public ResponseEntity<?> getDestinosPorNome(@RequestParam(name = "nome", required = true) String nome) {
    // Busque destinos pelo nome usando o repositório
    List<Destino> destinosEncontrados = destinosRepository.findAllByNome(nome);

    if (!destinosEncontrados.isEmpty()) {
        // Destinos encontrados, retorne a lista
        return ResponseEntity.ok(destinosEncontrados);
    } else {
        // Nenhum destino encontrado, retorne a mensagem de erro
        Map<String, String> mensagemErro = new HashMap<>();
        mensagemErro.put("mensagem", "Nenhum destino foi encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
    }
}
}
