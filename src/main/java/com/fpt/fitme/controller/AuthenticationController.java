package com.fpt.fitme.controller;

import com.fpt.fitme.domain.FitmeUserDetails;
import com.fpt.fitme.model.AuthenticationRequest;
import com.fpt.fitme.model.AuthenticationResponse;
import com.fpt.fitme.service.FitmeUserDetailsService;
import com.fpt.fitme.util.JwtUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = fitmeUserDetailsService
				.loadFakeLogin();
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/login")
	public ResponseEntity<FitmeUserDetails> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final FitmeUserDetails userDetails = fitmeUserDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails.getUser());
		userDetails.setJwtToken(jwt);

		return new ResponseEntity(userDetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
	public ResponseEntity refreshToken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		if (claims == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token or token not expired yet.");
		}
		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.generateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
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