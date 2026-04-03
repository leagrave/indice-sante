package com.hospital.indice_sante.repository;

import com.hospital.indice_sante.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}