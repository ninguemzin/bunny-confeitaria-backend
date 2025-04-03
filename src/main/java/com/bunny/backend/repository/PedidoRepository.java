package com.bunny.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bunny.backend.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	List<Pedido> findByStatus(String status);
}
