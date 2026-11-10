package com.ipargym.rest;

import org.springframework.web.bind.annotation.*;

import com.ipargym.dtos.LoginDto;
import com.ipargym.dtos.TokenDto;
import com.ipargym.servicios.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/usuarios")
@RequiredArgsConstructor
public class UsuarioRest {

	private final UsuarioService usuarioService;

	@PostMapping("/login")
	public TokenDto login(@Valid @RequestBody LoginDto dto) {

		return usuarioService.login(dto);
	}

}
