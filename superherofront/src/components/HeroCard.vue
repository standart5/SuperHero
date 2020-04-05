<template>
  <v-container>
    <v-card class="mx-auto">
      <v-list-item three-line>
        <v-list-item-avatar tile size="130" color="grey">
          <v-img :src="hero.image"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
            <v-layout row wrap>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Intelligence: <span class="font-weight-bold">{{hero.powerstats['intelligence']}}</span></v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Strength: <span class="font-weight-bold">{{hero.powerstats['strength']}}</span></v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Speed: <span class="font-weight-bold">{{hero.powerstats['speed']}}</span></v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Durability: <span class="font-weight-bold">{{hero.powerstats['durability']}}</span></v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Power: <span class="font-weight-bold">{{hero.powerstats['power']}}</span></v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Combat: <span class="font-weight-bold">{{hero.powerstats['combat']}}</span></v-card-subtitle>
              </v-flex>
            </v-layout>
        </v-list-item-content>
      </v-list-item>
      <v-card-title>{{hero.name}}</v-card-title>
      <v-card-text class="text--primary">        
        <v-layout row wrap>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Full name: <span class="font-weight-bold">{{hero.biography['full-name']}}</span></v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Publisher: <span class="font-weight-bold">{{hero.biography['publisher']}}</span></v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Alignment: <span class="font-weight-bold">{{hero.biography['alignment']}}</span></v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Place of birth: <span class="font-weight-bold">{{hero.biography['place-of-birth']}}</span></v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">First appearance: <span class="font-weight-bold">{{hero.biography['first-appearance']}}</span></v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Alter egos: <span class="font-weight-bold">{{hero.biography['alter-egos']}}</span></v-card-subtitle>
          </v-flex>
        </v-layout>        
      </v-card-text>
      <v-card-actions></v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
  export default {
    name: 'HeroCard',
    props:{
      hero:{type:Object},
    },
     data () {
      return {
        tab: null,
        items: [
          { tab: 'Hero', content: 'Tab 1 Content' },
          { tab: 'Powerstats', content: 'Tab 2 Content' },
          { tab: 'Biography', content: 'Tab 3 Content' },
        ],
      }
    },
  }
</script>
