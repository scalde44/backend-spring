package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioMapper usuarioMapper;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {
		List<Usuario> usuarios = usuarioService.findAll();
		List<UsuarioDTO> usuarioDTOs = usuarioMapper.toUsuarioDTOs(usuarios);
		return ResponseEntity.ok().body(usuarioDTOs);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
		Usuario usuario = usuarioMapper.toUsuario(usuarioDTO);
		usuario = usuarioService.save(usuario);
		usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
		Usuario usuario = usuarioMapper.toUsuario(usuarioDTO);
		usuario = usuarioService.update(usuario);
		usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@DeleteMapping("/delete/{email}")
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception {
		usuarioService.deleteById(email);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/findById/{email}")
	public ResponseEntity<?> findById(@PathVariable("email") String email) throws Exception {
		Optional<Usuario> usuarioOptional = usuarioService.findById(email);
		if (usuarioOptional.isPresent() == false) {
			return ResponseEntity.ok().body(null);
		}
		Usuario usuario = usuarioOptional.get();
		UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);
		return ResponseEntity.ok().body(usuarioDTO);
	}
}
