package com.archivsoft.nobelWill.repository;

import com.archivsoft.nobelWill.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String findByUsername);
    Page<User> findAllBy(Pageable pageable);

}

