package vtys_project.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vtys_project.forum.entity.Users;
import vtys_project.forum.repository.UsersRepository;

import java.util.Collection;
import java.util.Collections;


@Component
public class CustomUserDetailService implements UserDetailsService {

    UsersRepository usersRepository;

    @Autowired
    public CustomUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        // Assuming getRoles() returns a List<String> containing role names
        Collection<? extends GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(usersRepository.getRoles(user.getUserID())));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}
