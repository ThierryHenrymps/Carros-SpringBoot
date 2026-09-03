package com.api.car.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.car.carros.dto.CarroDTO;


@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros(){
 
       return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO getCarroById(Long id) {
        return rep.findById(id).map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("O carro nao foi encontrado", id));

    }
    
    public List<CarroDTO> getCarroByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

	public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(), "Nao foi possivel atualizar o registro");
		return CarroDTO.create(rep.save(carro));
	}

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull (id, "Nao foi possivel atualizar o registro");

    Optional<Carro> optional = rep.findById(id);
        if(optional.isPresent()) {
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            rep.save(db);

            return CarroDTO.create(db);
        } else {
            return null;
        }
    }
    
    public void delete(Long id) {
        rep.deleteById(id);
    }

    
 



  


   
}
