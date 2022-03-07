package org.generation.blog.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blog.models.UserLogin;
import org.generation.blog.models.Usuario;
import org.generation.blog.repository.UsuarioRepository;
import org.generation.blog.service.UsuarioService;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/buscar")
	public ResponseEntity <List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id) {
		return usuarioRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/buscar/Usuario/{Usuario}")
	public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable(value = "emailUsuario") String emailUsuario) {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(emailUsuario);		
		if (usuario.isPresent()) {
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não foi encontrado");
		}
	}
	
	@PostMapping("/logar")
		public ResponseEntity<UserLogin> autentication(@RequestBody Optional<UserLogin> user) {
		
		return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	/* public ResponseEntity<UserLogin>  Autentication(@Valid @RequestBody Optional<UserLogin> user) {
		
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	} */
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
		
		return usuarioService.cadastrarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	/* public ResponseEntity<Usuario>  Post(@Valid @RequestBody Usuario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	
	} */
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario (@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}
	
	/* public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
		return usuarioService.atualizarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	} */
	
	@DeleteMapping("/deletar/{idUsuario}")
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		
		if (usuario.isPresent()) {
			usuarioRepository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não foi encontrado");
		}
	}


}
