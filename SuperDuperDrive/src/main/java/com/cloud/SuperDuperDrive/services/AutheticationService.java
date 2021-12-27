package com.cloud.SuperDuperDrive.services;

import com.cloud.SuperDuperDrive.Mapper.UserMapper;
import com.cloud.SuperDuperDrive.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutheticationService implements AuthenticationProvider {

    private HashService hashService;
    private UserMapper userMapper;

    public AutheticationService(HashService hashService, UserMapper userMapper) {
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userMapper.getUser(username);
        if(user!=null)
        {
            String encodedSalt = user.getSalt();
            String hashPassword = hashService.getHashedValue(password, encodedSalt);
            if(user.getPassword().equals(hashPassword)){
                return new UsernamePasswordAuthenticationToken(username, password,new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
