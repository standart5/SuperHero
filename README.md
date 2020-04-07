# SuperHero

Backend - superheroback
Frontend - superherofront

******Needed:******
- maven
- docker
- docker-compose


******Instalation:******
1. In ***superheroback*** directory, run comand `mvn clean install`
    
    After executing the command, projects will be built relative to the instructions written in `pom.xml`
    Every service has own `pom.xml` and could be build individual. But `pom.xml` from ***superheroback*** has instruction for build all services together

2. In main directory ***SuperHero***, is `docker-compose.yml` file. In this file you should fill parameter `SUPER_HERO_API_ACCESS_TOKEN`. This parameter is a access token to SuperHeroApi.
      
3. In main directory ***SuperHero***, run comand `docker-compose up --build`. Wait for all services to start. When all services are acttive. You can use app on `http:localhost:8000`

   **superheroback** and ***superherofront*** have own `docker-compose.yml`, and you can run it separately, but remember to fill access token parameter in `docker-compose.yml` in ***superheroback*** directory
   for downloading ***SuperHeroes*** from ***SuperHeroApi***
