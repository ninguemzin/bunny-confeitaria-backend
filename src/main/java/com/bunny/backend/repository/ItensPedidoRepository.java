package com.bunny.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bunny.backend.model.ItensPedido;
import com.bunny.backend.model.Pedido;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long>{
	List<ItensPedido> findByPedido(Pedido pedido);
}
