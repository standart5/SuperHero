<template>
  <v-container>
    <v-layout row wrap>
      <v-flex xs12 sm12 md6 lg4 v-for="hero in heroes" :key="hero.id" >
        <HeroCard :hero="hero"/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
// @ is an alias to /src
import HeroCard from '@/components/HeroCard.vue'

export default {
  name: 'Heros',
  components: {
    HeroCard
  },
  data(){
    return{
      heroes:[],
    }
  },
  beforeMount(){
    this.getHeroes();
  },
  methods:{
    getHeroes(){
      const baseURI = 'http://localhost:8080/hero/getAllHeroes'
      this.$http.get(baseURI)
      .then((result) => {
        this.heroes = result.data
      })
    }
  }
}
</script>
