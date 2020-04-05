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
                <v-card-subtitle class="py-0">Intelligence: {{hero.powerstats['intelligence']}}</v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Strength: {{hero.powerstats['strength']}}</v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Speed: {{hero.powerstats['speed']}}</v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Durability: {{hero.powerstats['durability']}}</v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Power: {{hero.powerstats['power']}}</v-card-subtitle>
              </v-flex>
              <v-flex xs12>
                <v-card-subtitle class="py-0">Combat: {{hero.powerstats['combat']}}</v-card-subtitle>
              </v-flex>
            </v-layout>
        </v-list-item-content>
      </v-list-item>
      <v-card-title>{{hero.name}}</v-card-title>
      <v-card-text class="text--primary">        
        <v-layout row wrap>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Full name: {{hero.biography['full-name']}}</v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Publisher: {{hero.biography['publisher']}}</v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Alignment: {{hero.biography['alignment']}}</v-card-subtitle>
          </v-flex>
          <v-flex xs12>
            <v-card-subtitle class="py-0">Place of birth: {{hero.biography['place-of-birth']}}</v-card-subtitle>
          </v-flex>
          <v-flex xs6>
            <v-card-subtitle class="py-0">First appearance: {{hero.biography['first-appearance']}}</v-card-subtitle>
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
