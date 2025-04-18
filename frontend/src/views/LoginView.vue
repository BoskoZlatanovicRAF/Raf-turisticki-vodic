<template>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card mt-5">
            <div class="card-body">
              <h2 class="card-title text-center">Login</h2>
              <form @submit.prevent="login">
                <div class="mb-3">
                  <label for="email" class="form-label">Email</label>
                  <input type="email" class="form-control" id="email" v-model="email" required>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <input type="password" class="form-control" id="password" v-model="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Login</button>
              </form>
              <div class="mt-3 text-center">
                <button @click="goToRegister" class="btn btn-link">Register</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>
  
<script>
  export default {
    data() {
      return {
        email: '',
        password: ''
      };
    },

    created(){
      if (this.$route.query.email) {
        this.email = this.$route.query.email;
      } 
      if (this.$route.query.password) {
        this.password = this.$route.query.password;
      }
    },

    methods: {
      login() {
        axios.post('/korisnik/login', {
            email: this.email,
            password: this.password
        })
        .then(response => {
          const token = response.data;
          this.$store.commit('setToken', token);

          // Decode JWT token to extract user type
          const decodedToken = JSON.parse(atob(token.split('.')[1]));
          const userType = decodedToken.tip_korisnika;

          this.$store.commit('setUserType', userType);
          this.$store.commit('setUser', this.email);

          this.$router.push({ path: '/home' });
        })
        .catch(error => {
          console.error(error);
        });
      },
      goToRegister() {
        this.$router.push('/register');
      }
    }
  };
</script>
  
  <style scoped>
  /* Add any custom styles here if needed */
  </style>
  