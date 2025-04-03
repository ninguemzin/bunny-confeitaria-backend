package com.bunny.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bunny.backend.model.ItensPedido;
import com.bunny.backend.repository.ItensPedidoRepository;
import com.bunny.backend.repository.PedidoRepository;
import com.bunny.backend.repository.ProdutosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itensPedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ItensPedidoController {
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutosRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<ItensPedido>> getAll(){
		return ResponseEntity.ok(itensPedidoRepository.findAll());
	}
	
	@GetMapping("/pedido/{id}")
	public ResponseEntity<List<ItensPedido>> getByPedido(@PathVariable Long id){
		return pedidoRepository.findById(id)
				.map(pedido -> ResponseEntity.ok(itensPedidoRepository.findByPedido(pedido)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
    public ResponseEntity<ItensPedido> post(@Valid @RequestBody ItensPedido item) {
		if (pedidoRepository.existsById(item.getPedido().getId()) &&
	            produtoRepository.existsById(item.getProdutos().getId())) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(itensPedidoRepository.save(item));
	        }
	        return ResponseEntity.badRequest().build();
	    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { 
    	if (itensPedidoRepository.existsById(id)) {
            itensPedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
	
}