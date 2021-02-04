package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	Validator validator;
	@Override
	public void validate(Usuario entity) throws Exception {
		if(entity==null) {
			throw new Exception("El usuario es nulo");
		}
		Set<ConstraintViolation<Usuario>> constraintViolation=validator.validate(entity);
		if (constraintViolation.isEmpty() == false) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario save(Usuario entity) throws Exception {
		validate(entity);
		if(usuarioRepository.existsById(entity.getEmail())){
			throw new Exception("El usuario con email: " + entity.getEmail() + " ya existe");
		}
		return usuarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario entity) throws Exception {
		validate(entity);
		if(usuarioRepository.existsById(entity.getEmail())==false){
			throw new Exception("El usuario con email: " + entity.getEmail() + " no existe");
		}
		return usuarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuario entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		if (entity.getEmail() == null || entity.getEmail().isBlank() == true) {
			throw new Exception("El email es obligatorio");
		}
		if(usuarioRepository.existsById(entity.getEmail())==false){
			throw new Exception("El usuario con email: " + entity.getEmail() + " no existe");
		}
		usuarioRepository.deleteById(entity.getEmail());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null || id.isBlank() == true) {
			throw new Exception("El email es obligatorio");
		}
		if(usuarioRepository.existsById(id)) {
			delete(usuarioRepository.findById(id).get());
		}else {
			throw new Exception("El usuario con email: " + id + " no existe");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(String id) throws Exception {
		return usuarioRepository.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return usuarioRepository.count();
	}

}
