package com.superhero.persistence.dao.repository;

import com.superhero.persistence.domain.Aliases;
import com.superhero.persistence.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AliasesRepository extends JpaRepository<Aliases, Long> {
}