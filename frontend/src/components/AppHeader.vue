<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <router-link class="navbar-brand" to="/home">Početna</router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/destinacije">Destinacije</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/clanci">Članci</router-link>
          </li>
          <li class="nav-item" v-if="$store.getters.getUserType === 'admin'">
            <router-link class="nav-link" to="/korisnici">Korisnici</router-link>
          </li>
        </ul>
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <button class="btn btn-link nav-link" v-if="user" @click="logout">Odjavi se</button>
            <router-link class="btn btn-link nav-link" v-else to="/">Prijavi se</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  computed: {
    user() {
      return this.$store.getters.getUser;
    },
    token() {
      return this.$store.state.token;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('logout');
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>
/* Custom styles for navbar can be added here */
</style>
