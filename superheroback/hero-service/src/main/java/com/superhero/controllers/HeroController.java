package com.superhero.controllers;

import com.superhero.persistence.domain.Hero;
import com.superhero.services.api.HeroReader;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CommonsLog
public class HeroController {

    @Autowired
    HeroReader heroReader;

    @GetMapping("getHeroById")
    public Hero getHero(@PathVariable Long id){return heroReader.getHero(id);}

    @GetMapping("getHeroByName")
    public List<Hero> getHero(@PathVariable String name){return heroReader.getHero(name);}

    @GetMapping("getAllHeroes")
    public List<Hero> getAllHeroes(){return heroReader.getAllHeroes();}

    @GetMapping("filterHeroes")
    public List<Hero> filterHeroes(@RequestParam("value") String value){
        return heroReader.filterHeroes(value);
    }



}
