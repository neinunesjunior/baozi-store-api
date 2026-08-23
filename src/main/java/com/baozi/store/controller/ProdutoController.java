package com.baozi.store.controller;

import com.baozi.store.model.Produto;
import com.baozi.store.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // POST - Criar produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    // GET - Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    // GET /{id} - Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return ResponseEntity.ok(produto);
    }

    // DELETE /{id} - Deletar produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /{id} - Atualizar produto (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Long id,
            @Valid @RequestBody Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setEstoque(produtoAtualizado.getEstoque());

        Produto produtoSalvo = produtoRepository.save(produtoExistente);
        return ResponseEntity.ok(produtoSalvo);
    }

    // Endpoint adicional: listar produtos em estoque
    @GetMapping("/estoque")
    public ResponseEntity<List<Produto>> listarProdutosEmEstoque() {
        List<Produto> produtos = produtoRepository.findByEstoqueTrue();
        return ResponseEntity.ok(produtos);
    }
}