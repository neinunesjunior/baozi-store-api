package com.baozi.store.controller;

import com.baozi.store.model.Pedido;
import com.baozi.store.repository.PedidoRepository;
import com.baozi.store.repository.ClienteRepository;
import com.baozi.store.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // POST - Criar pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody Pedido pedido) {
        // Validar se o cliente existe
        if (!clienteRepository.existsById(pedido.getClienteId())) {
            throw new RuntimeException("Cliente não encontrado com ID: " + pedido.getClienteId());
        }

        // Validar se o produto existe
        if (!produtoRepository.existsById(pedido.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado com ID: " + pedido.getProdutoId());
        }

        Pedido novoPedido = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    // GET - Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodosPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    // GET /{id} - Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
        return ResponseEntity.ok(pedido);
    }

    // DELETE /{id} - Deletar pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado com ID: " + id);
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /{id} - Atualizar pedido (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(
            @PathVariable Long id,
            @Valid @RequestBody Pedido pedidoAtualizado) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));

        // Validar se o cliente existe
        if (!clienteRepository.existsById(pedidoAtualizado.getClienteId())) {
            throw new RuntimeException("Cliente não encontrado com ID: " + pedidoAtualizado.getClienteId());
        }

        // Validar se o produto existe
        if (!produtoRepository.existsById(pedidoAtualizado.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado com ID: " + pedidoAtualizado.getProdutoId());
        }

        pedidoExistente.setClienteId(pedidoAtualizado.getClienteId());
        pedidoExistente.setProdutoId(pedidoAtualizado.getProdutoId());
        pedidoExistente.setQuantidade(pedidoAtualizado.getQuantidade());

        Pedido pedidoSalvo = pedidoRepository.save(pedidoExistente);
        return ResponseEntity.ok(pedidoSalvo);
    }

    // Endpoints adicionais
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> listarPedidosPorCliente(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + clienteId);
        }
        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Pedido>> listarPedidosPorProduto(@PathVariable Long produtoId) {
        if (!produtoRepository.existsById(produtoId)) {
            throw new RuntimeException("Produto não encontrado com ID: " + produtoId);
        }
        List<Pedido> pedidos = pedidoRepository.findByProdutoId(produtoId);
        return ResponseEntity.ok(pedidos);
    }
}