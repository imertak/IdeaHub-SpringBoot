package vtys_project.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.dto.AuthResponseDto;
import vtys_project.forum.dto.LoginDto;
import vtys_project.forum.dto.RegisterDto;
import vtys_project.forum.entity.Roles;
import vtys_project.forum.entity.Users;
import vtys_project.forum.repository.RolesRepository;
import vtys_project.forum.repository.UsersRepository;
import vtys_project.forum.security.CustomUserDetailService;
import vtys_project.forum.security.JWTGenerator;

import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UsersRepository userRepository;
    private RolesRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    @Autowired
    private CustomUserDetailService customUserDetailService;




    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsersRepository userRepository, RolesRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator=jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto registerDto){
        System.out.println(registerDto);
        Users nameControlUser = userRepository.findByUsername(registerDto.getUsername());
        Users emailControlUser = userRepository.findByMail(registerDto.getEmail());
        if((nameControlUser.getUsername()!=null || emailControlUser.getEmail()!=null)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Users user = new Users();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setCreationDate(Calendar.getInstance().getTime());
        //user.setProfileImage(registerDto.getProfileImage());//profileImage BLOB tipinde

        Roles role = roleRepository.getRoleByRoleName("ROLE_USER");
        user.setRoleID(role.getRoleID());
        userRepository.addUser(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDto.getUsername(), registerDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtGenerator.generateToken(authentication);
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken,"Bearer ");
        return new ResponseEntity<>(authResponseDto ,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtGenerator.generateToken(authentication);
        System.out.println(accessToken);
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken,"Bearer ");
        return authResponseDto;
    }


    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @GetMapping("/tokenControl/{token}")
    public Boolean tokenController(@PathVariable String token){
        return jwtGenerator.validateToken(token);
    }

}

