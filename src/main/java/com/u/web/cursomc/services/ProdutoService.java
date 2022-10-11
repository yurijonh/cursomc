package com.u.web.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.u.web.cursomc.domain.Categoria;
import com.u.web.cursomc.domain.Produto;
import com.u.web.cursomc.repositories.CategoriaRepository;
import com.u.web.cursomc.repositories.ProdutoRepository;
import com.u.web.cursomc.services.exceptions.ObjNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired 
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: "+ Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String directon){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directon), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}

}
