package org.generation.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blog.models.Tema;
import org.generation.blog.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "*")
@RequestMapping("/temas")

public class TemaController {
	
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List< Tema >> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> GetByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByAssuntoContainingIgnoreCase(nome));
		
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Tema> post (@Valid @RequestBody Tema tema) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Tema> put (@Valid @RequestBody Tema tema) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@DeleteMapping("/deletar/{id}")

	public void delete(@PathVariable long id)  {

	repository.deleteById (id);

	}

}
