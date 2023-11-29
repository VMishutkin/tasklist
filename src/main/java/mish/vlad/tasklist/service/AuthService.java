package mish.vlad.tasklist.service;

public interface AuthService {
    JwtResponce login(JwtRequest loginRequest);

    JwtResponce refresh(String refreshToken);
}
