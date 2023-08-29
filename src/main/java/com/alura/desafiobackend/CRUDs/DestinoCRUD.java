package com.alura.desafiobackend.CRUDs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alura.desafiobackend.chatGpt3Integration;
import com.alura.desafiobackend.Entities.Destino;
import com.alura.desafiobackend.Repositories.DestinosRepository;

@RestController
@RequestMapping(path="/destinos")
public class DestinoCRUD {
  
  @Autowired
  DestinosRepository destinosRepository;

  @GetMapping(value="/todos")
  @ResponseBody
  public List<Destino> getDestinos() {
    return destinosRepository.findAll();
  }
  
  @PostMapping()
  @ResponseBody
  public Destino postDestino(@RequestBody Destino destino) {

    if(destino.getTextoDescritivo()!=null && destino.getTextoDescritivo().length()>0){
      destinosRepository.save(destino);
    }
    else{
      destino.setTextoDescritivo(chatGpt3Integration.gerarTextoDescritivo(destino.getNome()));
    }
      return destino;
  }

  @PutMapping()
  @ResponseBody
  public Destino putDestino(@RequestBody Destino newDestino) {
    Destino destino;
    try{
      destino = destinosRepository.findById(newDestino.getId()).get();
      destino.setNome(newDestino.getNome());
      destino.setPreco(newDestino.getPreco());
      destino.setFoto1(newDestino.getFoto1());
      destino.setFoto2(newDestino.getFoto2());
      destino.setTextoDescritivo(newDestino.getTextoDescritivo());
      destino.setMeta(newDestino.getMeta());
      destinosRepository.save(destino);
    } catch(Exception e){
      System.out.println(e);
      destino = null;
    }

    return destino;
  }

  @DeleteMapping()
  @ResponseBody
  public Destino deleteDestino(@RequestParam Long id) {
    Destino destino;
    try{
      destino = destinosRepository.findById(id).get();
      destinosRepository.delete(destino);
    } catch(Exception e){
      System.out.println(e);
      destino = null;
    }

    return destino;
  }

  //pegar os destinos pelo id

  @GetMapping(value="/{id}")
  @ResponseBody
  public ResponseEntity<?> getDestinoById(@RequestParam Long id) {
    Optional<Destino> destino = destinosRepository.findById(id);

    if (destino.isPresent()) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(destino.get());
    } else {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Nenhum destino foi encontrado com o ID fornecido.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(resposta);
    }
  }

}
