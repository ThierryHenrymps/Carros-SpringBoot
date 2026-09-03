package com.api.car.carros.domain;

import java.net.http.WebSocket.Listener;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);

}
