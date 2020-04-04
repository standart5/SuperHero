package com.superhero.persistence.dao.repository;


import com.superhero.persistence.domain.Biography;
import com.superhero.persistence.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiographyRepository extends JpaRepository<Biography, Long> {
}