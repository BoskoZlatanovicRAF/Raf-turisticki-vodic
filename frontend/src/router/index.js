import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import DestinacijaView from '../views/DestinacijaView.vue'
import ClanakView from '../views/ClanakView.vue'
import KorisniciView from '../views/KorisniciView.vue'
import ClanakPocetnaStranicaView from '../views/ClanakPocetnaStranicaView.vue'
import store from '../store/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView
  },
  {
    path: '/home',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/destinacije',
    name: 'Destinacije',
    component: DestinacijaView
  },
  {
    path: '/clanci',
    name: 'Članci',
    component: ClanakView
  },
  {
    path: '/korisnici',
    name: 'Korisnici',
    component: KorisniciView,
    meta: { requiresAdmin: true }
  },
  {
    path: '/clanak/:id',
    name: 'ClanakPocetnaStranicaView',
    component: ClanakPocetnaStranicaView
  },
  {
    path: '/destinacija/:ime',
    name: 'DestinacijaView',
    component: DestinacijaView
  },
  {
    path: '/',
    redirect: '/home'
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  const userType = store.getters.getUserType;
  if (to.matched.some(record => record.meta.requiresAdmin)) {
    if (userType === 'admin') {
      next();
    } else {
      next('/home');
    }
  } else {
    next();
  }
});

export default router
