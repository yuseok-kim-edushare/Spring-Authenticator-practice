package com.example.authservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.authservice.entity.User;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*
     * this is used to find the user by username
     */
    Optional<User> findByUsername(String username);
    /*
     * this is used to find the user by email
     */
    Optional<User> findByEmail(String email);
    /*
     * this is used to find the user by phone number
     */
    Optional<User> findByPhoneNumber(String phoneNumber);
    /*
     * this is used to find the user by login ID
     */
    Optional<User> findByLoginID(String loginID);
}

