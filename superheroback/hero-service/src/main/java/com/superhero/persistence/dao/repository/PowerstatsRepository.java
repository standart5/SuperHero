package com.superhero.persistence.dao.repository;

import com.superhero.persistence.domain.Powerstats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerstatsRepository extends JpaRepository<Powerstats, Long> {
}