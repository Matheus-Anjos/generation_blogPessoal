






package org.generation.blog.controller;

import org.generation.blog.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.generation.blog.models.Postagem;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List< Postagem >> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
}
