package com.tech.fat.api.controller;

import com.tech.fat.api.model.AuthRequest;
import com.tech.fat.api.service.UserDetailsServiceImpl;
import com.tech.fat.api.util.JwtUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("authenticate")
    public ResponseEntity createToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),
                            authRequest.getPassword()));
        } catch (Exception exception) {
            return new ResponseEntity<>("Authentication failed.", HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        Collection<SimpleGrantedAuthority> roles = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
        JSONArray list = new JSONArray();
        roles.forEach(role-> list.add(role.getAuthority()));
        JSONObject resJson = new JSONObject();
        resJson.put("roles", list);
        resJson.put("token", jwtUtil.generateToken(authRequest.getUserName()));
        return new ResponseEntity<>(resJson, HttpStatus.OK);
    }
}
