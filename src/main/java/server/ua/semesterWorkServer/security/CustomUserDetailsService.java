package server.ua.semesterWorkServer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.ua.semesterWorkServer.repos.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Username to found: " + username);

        UserDetails userDetails = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User " + username + " wasn't found!"));

        return userDetails;
    }
}
