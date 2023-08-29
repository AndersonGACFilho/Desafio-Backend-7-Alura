package com.alura.desafiobackend.CRUDs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alura.desafiobackend.Entities.Depoimento;
import com.alura.desafiobackend.Repositories.DepoimentosRepository;

@RestController
public class DepoimentoCRUD {

    @Autowired
    DepoimentosRepository depoimentosRepository;
    
    @GetMapping(value="/depoimentos")
    @ResponseBody
    public List<Depoimento> getDepoimentos() {
        List<Depoimento> depoimentos = depoimentosRepository.findAll();

        return depoimentos;
    }

    @PostMapping(value="/depoimentos")
    @ResponseBody
    public Depoimento postDepoimento(@RequestBody Depoimento depoimento) {
        depoimentosRepository.save(depoimento);

        return depoimento;
    }
    
    @PutMapping(value="/depoimentos")
    @ResponseBody
    public Depoimento putDepoimento(@RequestBody Depoimento newDepoimento) {
        Depoimento depoimento;
        try{
        depoimento = depoimentosRepository.findById(newDepoimento.getId()).get();
        
        depoimento.setNome(newDepoimento.getNome());
        depoimento.setDepoimento(newDepoimento.getDepoimento());
        depoimento.setFoto(newDepoimento.getFoto());
        depoimentosRepository.save(depoimento);
        } catch(Exception e){
            System.out.println(e);
            depoimento = null;
        }

        return depoimento;
    }

    @DeleteMapping(value="/depoimentos")
    @ResponseBody
    public Depoimento deleteDepoimento(Long id) {
        Depoimento depoimento;
        try{
        depoimento = depoimentosRepository.findById(id).get();
        depoimentosRepository.delete(depoimento);
        } catch(Exception e){
            System.out.println(e);
            depoimento = null;
        }

        return depoimento;
    }
}
