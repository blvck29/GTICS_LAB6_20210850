package com.example.lab6.Repository;

import com.example.lab6.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoReserva extends JpaRepository<Reserva, Integer> {
}
