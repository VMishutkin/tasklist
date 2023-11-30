package mish.vlad.tasklist.service.impl;

import mish.vlad.tasklist.service.AuthService;
import mish.vlad.tasklist.web.dto.auth.JwtRequest;
import mish.vlad.tasklist.web.dto.auth.JwtResponce;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponce login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponce refresh(String refreshToken) {
        return null;
    }
}
