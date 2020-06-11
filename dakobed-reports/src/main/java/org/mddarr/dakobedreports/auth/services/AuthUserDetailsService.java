package org.mddarr.dakobedreports.auth.services;


import org.mddarr.dakobedreports.auth.dao.UserRepository;
import org.mddarr.dakobedreports.auth.models.RegistrationRequest;
import org.mddarr.dakobedreports.auth.models.RegistrationResponse;
import org.mddarr.dakobedreports.auth.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = new User("user1","iksarman", new ArrayList<>());
//        return user;
//    }
    public UserEntity findByUsername(String username ) throws UsernameNotFoundException {
        UserEntity u = userRepository.findByUsername( username );
        return u;
    }

    @Override
    public UserEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = new UserEntity(UUID.randomUUID().toString(),"user1","iksarman","silly@gmail.com",  new ArrayList<>());
        return user;
    }

    public boolean authenticate(String username, String password) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        System.out.println("user password " + user.getPassword() + " " + user.getPassword().length());
        System.out.println("user password " + password + " " + password.length());
        if(user.getPassword() != password){
            System.out.println("WHat");
        }else{
            System.out.println("WHy");
        }
        return user.getPassword() == password;
    }





    public RegistrationResponse registerUser(RegistrationRequest registrationRequest){
        userRepository.save(new UserEntity(UUID.randomUUID().toString(), registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getEmail(), new ArrayList<>()));
        return new RegistrationResponse();
    }


}
