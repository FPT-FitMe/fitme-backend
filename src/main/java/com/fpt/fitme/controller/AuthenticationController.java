package com.fpt.fitme.controller;

import com.fpt.fitme.domain.FitmeUserDetails;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.model.AuthenticationRequest;
import com.fpt.fitme.model.AuthenticationResponse;
import com.fpt.fitme.model.FitMeUser;
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
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
		}

		final UserDetails userDetails = fitmeUserDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails);
		final FitMeUser fitMeUser = fitmeUserDetailsService.getUserInfo(authenticationRequest.getEmail());

		return new ResponseEntity(new FitmeUserDetails(fitMeUser, jwt), HttpStatus.OK);
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

	private Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody FitMeUser fitMeUser) {
		try {
			AppUser appUser = fitmeUserDetailsService.register(fitMeUser);
			return new ResponseEntity(appUser, HttpStatus.CREATED);
		} catch (Exception e) {
			if (e.getMessage().contains("constraint [uk")) {
				return new ResponseEntity("Email is duplicated", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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