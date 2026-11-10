package com.ipargym.servicios;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ipargym.dtos.LoginDto;
import com.ipargym.dtos.TokenDto;
import com.ipargym.entidades.Usuario;
import com.ipargym.repositorios.UsuarioRepository;
import com.ipargym.seguridad.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final AuthenticationManager authenticationManager;
	private final UsuarioRepository usuarioRepository;
	private final JwtService jwtService;

	public TokenDto login(LoginDto dto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

		Usuario usuario = usuarioRepository.findByEmail(authentication.getName())
				.orElseThrow(() -> new BadCredentialsException("Credenciales incorrectas"));

		String token = jwtService.generarToken(usuario);

		return new TokenDto(token, "Bearer");
	}

}
