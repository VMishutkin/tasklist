package mish.vlad.tasklist.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.user.User;
import mish.vlad.tasklist.service.AuthService;
import mish.vlad.tasklist.service.UserService;
import mish.vlad.tasklist.web.dto.auth.JwtRequest;
import mish.vlad.tasklist.web.dto.auth.JwtResponce;
import mish.vlad.tasklist.web.dto.user.UserDto;
import mish.vlad.tasklist.web.mapper.UserMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name= "Auth controller", description = "Auth API")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public JwtResponce login(@Validated @RequestBody JwtRequest loginRequest) {

        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public UserDto register (@RequestBody UserDto userDto) {
        //System.out.println(userDto);
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }
    @PostMapping("/refresh")
    public JwtResponce refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }

}
