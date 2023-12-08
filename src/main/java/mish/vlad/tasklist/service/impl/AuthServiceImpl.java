package mish.vlad.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.user.User;
import mish.vlad.tasklist.service.AuthService;
import mish.vlad.tasklist.service.UserService;
import mish.vlad.tasklist.web.dto.auth.JwtRequest;
import mish.vlad.tasklist.web.dto.auth.JwtResponce;
import mish.vlad.tasklist.web.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public JwtResponce login(JwtRequest loginRequest) {
        JwtResponce jwtResponce = new JwtResponce();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = userService.getByUsername(loginRequest.getUsername());
        jwtResponce.setId(user.getId());
        jwtResponce.setUsername(user.getUsername());
        jwtResponce.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()));
        jwtResponce.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));
        return jwtResponce;
    }

    @Override
    public JwtResponce refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);

    }
}
