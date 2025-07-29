package br.com.marllonbruno.fitnesstracker.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.marllonbruno.fitnesstracker.api.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
