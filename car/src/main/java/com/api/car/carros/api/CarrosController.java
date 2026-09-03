package com.api.car.carros.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.car.carros.domain.Carro;
import com.api.car.carros.domain.CarroService;
import com.api.car.carros.dto.CarroDTO;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/carros")

public class CarrosController {

    @Autowired
    private CarroService service;
    
    @GetMapping
    public ResponseEntity<List<CarroDTO>> get() {
    return ResponseEntity.ok(service.getCarros());
}

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable ("id") Long id) {
        CarroDTO carro = service.getCarroById(id);

        return ResponseEntity.ok(carro);
        
    }
    
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity <List<CarroDTO>>getCarroByTipo(@PathVariable ("tipo") String tipo) {
        List<CarroDTO> carro = service.getCarroByTipo(tipo);

        return carro.isEmpty() ?
        ResponseEntity.noContent().build() :
        ResponseEntity.ok(carro);
        
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Carro carro) {
       
        try{
            CarroDTO c = service.insert(carro);

            URI location = getUri(c.getId());

            return ResponseEntity.created(null).build();

        }catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        
    }
    private URI getUri(Long id) {
            return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(id).toUri();
        }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable("id") Long id, @RequestBody Carro carro){   
    
        CarroDTO c = service.update(carro, id);

        return ResponseEntity.badRequest().build();

    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
         service.delete(id);

    return ResponseEntity.noContent().build();
    }
    


}