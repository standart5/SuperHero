import Vue from 'vue'
import VueRouter from 'vue-router'
import Heros from '../views/Heros.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Heros',
    component: Heros
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
