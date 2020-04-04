package com.superhero.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.superhero.persistence.dao.repository.AliasesRepository;
import com.superhero.persistence.dao.repository.BiographyRepository;
import com.superhero.persistence.dao.repository.HeroRepository;
import com.superhero.persistence.dao.repository.PowerstatsRepository;
import com.superhero.persistence.domain.Aliases;
import com.superhero.persistence.domain.Biography;
import com.superhero.persistence.domain.Hero;
import com.superhero.persistence.domain.Powerstats;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class SuperHeroServiceImpl {

    @Value(value = "${super-hero-api-url}")
    private String apiUrl;

    @Value(value = "${super-hero-api-access-token}")
    private String apiAccessToken;

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PowerstatsRepository powerstatsRepository;

    @Autowired
    BiographyRepository biographyRepository;

    @Autowired
    AliasesRepository aliasesRepository;


    @PostConstruct
    public void init() throws JsonProcessingException {
        String result = getHeroFromExternalApi();
        Hero hero = convertToHero(result);
        saveInDB(hero);
        log.error(hero);
    }

    public String getHeroFromExternalApi(){
        final String uri = apiUrl+"/"+apiAccessToken+"/69";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    public Hero convertToHero(String value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(value);
        Powerstats powerstats = objectMapper.readValue(jsonNode.get("powerstats").toString(),Powerstats.class);
        Biography biography = objectMapper.readValue(jsonNode.get("biography").toString(),Biography.class);
        biography.setAliases(getAliases(jsonNode));
        return Hero.builder()
                .externalId(jsonNode.get("id").asLong())
                .name(jsonNode.get("name").toString())
                .image(jsonNode.get("image").get("url").toString())
                .biography(biography)
                .powerstats(powerstats)
                .build();
    }

    public Set<Aliases> getAliases(JsonNode jsonNode){
        String aliases = jsonNode.get("biography").get("aliases").toString();
        return Arrays.asList(aliases.replace("[","").replace("]","").split(","))
                .parallelStream()
                .map(v->Aliases.builder().value(v).build())
                .collect(Collectors.toSet());
    }

    @Transactional
    public Hero saveInDB(Hero hero){
        Hero oldHero = heroRepository.findByExternalId(hero.getExternalId());
        if(oldHero!=null){
            hero.setId(oldHero.getId());
            hero.getBiography().setId(oldHero.getBiography().getId());
            hero.getPowerstats().setId(oldHero.getPowerstats().getId());
        }
        biographyRepository.save(hero.getBiography());
        powerstatsRepository.save(hero.getPowerstats());
        heroRepository.save(hero);
        return hero;
    }



}
