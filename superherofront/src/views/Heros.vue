<template>
  <v-container>
    <v-layout row wrap>
      <v-flex xs12 sm6 md4 lg3 v-for="hero in heroes" :key="hero.id" >
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
    this.$eventBus.$on('saerch',this.onSearch)
    this.getHeroes("");
  },
  beforeDestroy(){
    this.$eventBus.$of('saerch',this.onSearch)
  },
  methods:{
    onSearch(value){
      this.getHeroes(value)
    },
    getHeroes(value){
      const baseURI = 'http://localhost:8080/hero/filterHeroes'
      this.$http.get(baseURI, {params: {value: value}})
      .then((result) => {
        this.heroes = result.data
      })
    }
  }
}
</script>
