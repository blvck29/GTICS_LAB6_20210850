package com.example.lab6.Repository;

import com.example.lab6.Entity.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDispositivo extends JpaRepository<Dispositivo, Integer> {
}
