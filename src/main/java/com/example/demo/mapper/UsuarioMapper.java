package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioDTO;

@Mapper
public interface UsuarioMapper {
	public UsuarioDTO toUsuarioDTO(Usuario usuario);
	public Usuario toUsuario(UsuarioDTO usuarioDTO);
	
	public List<UsuarioDTO> toUsuarioDTOs(List<Usuario> usuarios);
	public List<Usuario> toUsuarios(List<UsuarioDTO> usuarioDTOs);
}
