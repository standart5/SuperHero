package com.superhero.services.impl;

import com.superhero.persistence.dao.repository.HeroRepository;
import com.superhero.persistence.domain.Hero;
import com.superhero.services.api.HeroReader;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public List<Hero> filterHeroes(String value) {
        return heroRepository.findAll(globalHeroFilters(value));
    }

    private Specification<Hero> globalHeroFilters(String value){
        return (root, query, cb) -> {
            String criteriaValue = "%"+value.toLowerCase()+"%";
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(cb.lower(root.get("name")),criteriaValue));
            predicates.add(cb.like(cb.lower(root.get("biography").get("fullName")),criteriaValue));
            predicates.add(cb.like(cb.lower(root.get("biography").get("alterEgos")),criteriaValue));
            predicates.add(cb.like(cb.lower(root.get("biography").get("firstAppearance")),criteriaValue));
            predicates.add(cb.like(cb.lower(root.get("biography").get("publisher")),criteriaValue));
           // predicates.add(cb.like(cb.lower(root.get("biography").get("alignment")),criteriaValue));
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
