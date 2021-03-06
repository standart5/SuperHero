package com.superhero.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.persistence.dao.repository.BiographyRepository;
import com.superhero.persistence.dao.repository.HeroRepository;
import com.superhero.persistence.dao.repository.PowerstatsRepository;
import com.superhero.persistence.domain.Biography;
import com.superhero.persistence.domain.Hero;
import com.superhero.persistence.domain.Powerstats;
import com.superhero.utils.SuperHeroUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class SuperHeroETLService {

    @Value(value = "${super.hero.api.url}")
    private String apiUrl;

    @Value(value = "${super.hero.api.access.token}")
    private String apiAccessToken;

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PowerstatsRepository powerstatsRepository;

    @Autowired
    BiographyRepository biographyRepository;

    @Autowired
    SuperHeroUtil superHeroUtil;

    @PostConstruct
    public void init(){
        this.runInNewThread();
    }

    private void runInMainThread(){
        Set<Integer> ids = superHeroUtil.getRandomIds();
        log.info("start Extract Transfer and Load Process  for super heroes by ids: "+ids);
        for(Integer id : ids){
            try {
                String result = this.getHeroFromExternalApi(id);
                Hero hero = this.convertToHero(result);
                this.saveInDB(hero);
            } catch (Exception e) {
                log.error("Can not download super heroes - "+e.getMessage());
                e.printStackTrace();
                break;
            }
        }
        log.info("Stop ETL process for super heroes");
    }

    private void runInNewThread(){
        Runnable task = () -> {
            Set<Integer> ids = superHeroUtil.getRandomIds();
            log.info("start Extract Transfer and Load Process  for super heroes by ids: "+ids);
            for(Integer id : ids){
                try {
                    String result = this.getHeroFromExternalApi(id);
                    Hero hero = this.convertToHero(result);
                    this.saveInDB(hero);
                } catch (Exception e) {
                    log.error("Can not download super heroes - "+e.getMessage());
                    e.printStackTrace();
                    break;
                }
            }
            log.info("Stop ETL process for super heroes");
        };
        new Thread(task).start();
    }

    private String getHeroFromExternalApi(int id) throws Exception {
        final String uri = apiUrl+"/"+apiAccessToken+"/"+id;
        RestTemplate restTemplate = new RestTemplate();
        String response =  restTemplate.getForObject(uri, String.class);
        superHeroUtil.checkResponse(response);
        return response;
    }

    private Hero convertToHero(String value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(value);
        return Hero.builder()
                .externalId(jsonNode.get("id").asLong())
                .name(jsonNode.get("name").asText())
                .image(jsonNode.get("image").get("url").asText())
                .biography(objectMapper.readValue(jsonNode.get("biography").toString(),Biography.class))
                .powerstats(objectMapper.readValue(jsonNode.get("powerstats").toString(),Powerstats.class))
                .build();
    }



    @Transactional
    private Hero saveInDB(Hero hero){
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
