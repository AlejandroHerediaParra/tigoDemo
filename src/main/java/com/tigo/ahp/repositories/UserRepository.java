package com.tigo.ahp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tigo.ahp.models.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findFirstByEmail(String email);
}
