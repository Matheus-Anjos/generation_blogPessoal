package org.generation.blog.repository;

import java.util.List;

import org.generation.blog.models.Tema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository< Tema ,Long> {
	
	public List<Tema> findAllByAssuntoContainingIgnoreCase(String assunto);

}
