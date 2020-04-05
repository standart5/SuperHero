<template>
  <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp" app color="red darken-3" dark >
      <v-toolbar-title style="" class="ml-0 pl-4" >
        <span class="font-weight-black">Super Heroes</span>
      </v-toolbar-title>
      <v-spacer />
      <v-text-field 
        flat solo-inverted  hide-details  
        prepend-inner-icon="mdi-magnify" label="Search" 
        @change="onChange"/>
      <v-spacer />
    </v-app-bar>
</template>

<script>

export default {
  name: 'Toolbar',
  methods:{
    onChange(value){
      this.$eventBus.$emit('saerch', value) 
    }
  }
}
</script>
