package io.rl.spring_batch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.rl.spring_batch.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
