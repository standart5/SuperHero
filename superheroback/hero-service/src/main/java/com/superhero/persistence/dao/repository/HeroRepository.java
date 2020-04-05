package com.superhero.persistence.dao.repository;

import com.superhero.persistence.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long>, JpaSpecificationExecutor<Hero> {
    List<Hero> findByName(String name);
    Hero findByExternalId(Long externalId);
}