<template>
    <div class="container">
      <h2 class="mb-3">Register</h2>
      <div class="row">
        <div class="col-lg-6 offset-lg-3">
          <form @submit.prevent="register" class="needs-validation" novalidate>
            <div class="form-group">
              <label for="email">Email:</label>
              <input type="email" id="email" v-model="email" class="form-control" required>
            </div>
  
            <div class="form-group">
              <label for="password">Password:</label>
              <input type="password" id="password" v-model="password" class="form-control" required>
            </div>
  
            <div class="form-group">
              <label for="ime_prezime">Ime i prezime:</label>
              <input type="text" id="ime_prezime" v-model="ime_prezime" class="form-control" required>
            </div>
  
            <button type="submit" class="btn btn-primary">Register</button>
          </form>
        </div>
      </div>
    </div>
  </template>

<script>

export default {
  data() {
    return {
      email: '',
      password: '',
      ime_prezime: ''
    }
  },
  methods: {
    register() {
      axios.post('/korisnik/register', {
        email: this.email,
        password: this.password,
        ime_prezime: this.ime_prezime
      })
      .then(response => {
        console.log(response.data);
        this.$router.push({ path: '/' , query: { email: this.email, password: this.password }});
      })
      .catch(error => {
        console.error(error);
        // Handle the error here. For example, you might display an error message to the user.
      });
    }
  }
}
</script>