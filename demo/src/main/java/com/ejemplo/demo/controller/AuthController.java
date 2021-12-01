package com.ejemplo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.demo.model.Usuario;
import com.ejemplo.demo.service.UsuarioService;
import com.ejemplo.demo.util.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService UsuarioService;

    @Autowired
    private JWTUtil jwtUtil;
    
    @PostMapping
	public ResponseEntity<?> createtoken(@RequestBody Usuario usuarioDetalle){
		List<Usuario> Usuario = UsuarioService.login(usuarioDetalle.getUsername(), usuarioDetalle.getPassword());
		if(Usuario!=null && Usuario.size()>0) {
			String tokenJwt = jwtUtil.getJWTToken(Usuario.get(0).getUsername()+"");
            String resp = "{\"access\":\""+tokenJwt+"\"}";
            return ResponseEntity.ok(resp);
		} 
		return ResponseEntity.notFound().build();
	}
}