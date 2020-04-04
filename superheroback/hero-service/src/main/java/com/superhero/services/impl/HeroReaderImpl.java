package com.superhero.services.impl;

import com.superhero.persistence.dao.repository.HeroRepository;
import com.superhero.persistence.domain.Hero;
import com.superhero.services.api.HeroReader;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CommonsLog
public class HeroReaderImpl implements HeroReader {
    @Autowired
    HeroRepository heroRepository;


    @Override
    public Hero getHero(Long id) {
        return heroRepository.getOne(id);
    }

    @Override
    public List<Hero> getHero(String name) {
        return heroRepository.findByName(name);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    @Override
    public List<Hero> filterHeroes() {
        return null;
    }
}
