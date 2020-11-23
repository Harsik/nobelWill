package com.archivsoft.nobelWill.service;

import com.archivsoft.nobelWill.model.Role;
import com.archivsoft.nobelWill.model.User;
import com.archivsoft.nobelWill.payload.SignRequest;
import com.archivsoft.nobelWill.repository.UserRepository;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(SignRequest signRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(signRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signRequest.getPassword()));
        List<String> roles = new ArrayList<>();
        roles.add(String.valueOf(Role.ADMIN));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("user is not exists"));
        detailsChecker.check(user);
        return user;
    }


}
