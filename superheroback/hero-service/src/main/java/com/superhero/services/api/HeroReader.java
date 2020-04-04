package com.superhero.services.api;

import com.superhero.persistence.domain.Hero;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HeroReader {

    Hero getHero(Long id);
    List<Hero> getHero(String name);
    List<Hero> getAllHeroes();
    List<Hero> filterHeroes();
}
