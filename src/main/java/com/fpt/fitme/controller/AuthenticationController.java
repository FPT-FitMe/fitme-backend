package com.fpt.fitme.controller;

import com.fpt.fitme.model.AuthenticationRequest;
import com.fpt.fitme.model.AuthenticationResponse;
import com.fpt.fitme.service.FitmeUserDetailsService;
import com.fpt.fitme.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	
	private static final String resourceDir = "authentication/";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private FitmeUserDetailsService fitmeUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("login2")
	public ResponseEntity<?> fakeLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = fitmeUserDetailsService
				.loadFakeLogin();
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = fitmeUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	
	@GetMapping("/accountInfo") 
	public String accountInfo(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("userDetails", userDetails);
		return resourceDir + "accountInfo";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		return "authentication/403";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
}