package mish.vlad.tasklist.service;

import mish.vlad.tasklist.web.dto.auth.JwtRequest;
import mish.vlad.tasklist.web.dto.auth.JwtResponce;

public interface AuthService {
    JwtResponce login(JwtRequest loginRequest);

    JwtResponce refresh(String refreshToken);
}
