package com.u.web.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u.web.cursomc.domain.Cliente;
import com.u.web.cursomc.repositories.ClienteRepository;
import com.u.web.cursomc.services.exceptions.ObjNotFoundException;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: "+ Cliente.class.getName()));
	}

}
