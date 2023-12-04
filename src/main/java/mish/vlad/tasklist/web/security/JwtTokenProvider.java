package mish.vlad.tasklist.web.security;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.service.UserService;
import mish.vlad.tasklist.service.props.JwtProperties;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
private final JwtProperties jwtProperties;
private final UserDetailsService userDetailsService;
private final UserService userService;
private Key key;

@PostConstruct
    public void init(){
    this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
}


}
