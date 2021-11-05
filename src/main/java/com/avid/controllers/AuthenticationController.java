package com.avid.controllers;

import com.avid.helpers.JwtHelper;
import com.avid.models.entities.AuthenticationRequest;
import com.avid.models.entities.AuthenticationResponse;
import com.avid.services.MyUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private MyUserDetailService userDetailService;

  @Autowired
  private JwtHelper jwtTokenHelper;

  @PostMapping
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticateionRequest)
  throws Exception {

    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticateionRequest.getUsername(), authenticateionRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Incorect Username or Password");
    }

    final UserDetails userDetails = userDetailService.loadUserByUsername(authenticateionRequest.getUsername());

    final String jwt = jwtTokenHelper.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
