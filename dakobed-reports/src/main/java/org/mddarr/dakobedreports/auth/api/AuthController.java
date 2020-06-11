package org.mddarr.dakobedreports.auth.api;

import org.mddarr.dakobedreports.auth.models.*;
import org.mddarr.dakobedreports.auth.security.JwtUtil;
import org.mddarr.dakobedreports.auth.services.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AuthUserDetailsService authUserDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;



    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws AuthenticationException, IOException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        System.out.println("I AM AUTHETNETICATED " + authentication.isAuthenticated());
        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        UserEntity user = (UserEntity)authentication.getPrincipal();
        final String jwt = jwtTokenUtil.generateToken(user);
        Date expires = jwtTokenUtil.extractExpiration(jwt);
        // Return the token
        return ResponseEntity.ok(new UserTokenState(jwt, expires,"Charles"));
    }



    @RequestMapping(value = "/authenticate", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

//            UserEntity user = authUserDetailsService.findByUsername(authenticationRequest.getUsername());
//
//            boolean valid = authUserDetailsService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//            System.out.println("Correct credentaills " + valid);
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        }
        catch (BadCredentialsException e){
            System.out.println("Wahdfdf");
            throw new Exception("Incorrect username or password", e);
        }


        final UserEntity userDetails = authUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/register/", method= RequestMethod.POST)
    public ResponseEntity<?> registrationRequest(@RequestBody RegistrationRequest registrationRequest) throws Exception{
        authUserDetailsService.registerUser(registrationRequest);
        String jwt = "df";
        return ResponseEntity.ok(new RegistrationResponse(jwt));
    }


    @RequestMapping({"/hello"})
    public String hello(){
        return "HEllo bitches";
    }


}
