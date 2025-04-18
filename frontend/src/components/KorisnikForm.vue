<template>
    <div>
      <form @submit.prevent="saveKorisnik" class="form-horizontal">
        <div class="form-group row">
          <label for="ime_prezime" class="col-sm-2 col-form-label">Ime i prezime:</label>
          <div class="col-sm-10">
            <input type="text" id="ime_prezime" v-model="ime_prezime" class="form-control" required />
          </div>
        </div>
        <div class="form-group row">
          <label for="email" class="col-sm-2 col-form-label">Email:</label>
          <div class="col-sm-10">
            <input type="email" id="email" v-model="email" class="form-control" required :disabled="isEditing" />
          </div>
        </div>
        <div class="form-group row">
          <label for="tip_korisnika" class="col-sm-2 col-form-label">Tip korisnika:</label>
          <div class="col-sm-10">
            <select v-model="tip_korisnika" id="tip_korisnika" class="form-control" required>
              <option value="admin">Administrator</option>
              <option value="editor">Uređivač sadržaja</option>
            </select>
          </div>
        </div>
        <div v-if="!isEditing" class="form-group row">
          <label for="password" class="col-sm-2 col-form-label">Lozinka:</label>
          <div class="col-sm-10">
            <input type="password" id="password" v-model="password" class="form-control" required />
          </div>
        </div>
        <div v-if="!isEditing" class="form-group row">
          <label for="confirm_password" class="col-sm-2 col-form-label">Potvrda lozinke:</label>
          <div class="col-sm-10">
            <input type="password" id="confirm_password" v-model="confirm_password" class="form-control" required />
          </div>
        </div>
        <div class="form-group row">
          <div class="col-sm-10 offset-sm-2">
            <button type="submit" class="btn btn-primary">{{ isEditing ? 'Izmeni' : 'Dodaj' }} korisnika</button>
          </div>
        </div>
      </form>
      <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      initialImePrezime: { type: String, default: '' },
      initialEmail: { type: String, default: '' },
      initialTipKorisnika: { type: String, default: 'editor' },
      isEditing: { type: Boolean, default: false },
    },
    data() {
      return {
        ime_prezime: this.initialImePrezime,
        email: this.initialEmail,
        tip_korisnika: this.initialTipKorisnika,
        password: '',
        confirm_password: '',
        errorMessage: '',
      };
    },
    watch: {
      initialImePrezime(newVal) { this.ime_prezime = newVal; },
      initialEmail(newVal) { this.email = newVal; },
      initialTipKorisnika(newVal) { this.tip_korisnika = newVal; },
    },
    methods: {
      async saveKorisnik() {
        if (!this.ime_prezime || !this.email || !this.tip_korisnika || (!this.isEditing && (!this.password || !this.confirm_password))) {
          this.errorMessage = 'Sva polja su obavezna.';
          return;
        }
  
        if (!this.isEditing && this.password !== this.confirm_password) {
          this.errorMessage = 'Lozinke se ne poklapaju.';
          return;
        }
  
        try {
          if (this.isEditing) {
            await axios.put(`/korisnik/${this.email}`, {
              ime_prezime: this.ime_prezime,
              tip_korisnika: this.tip_korisnika,
            });
          } else {
            await axios.post('/korisnik', {
              email: this.email,
              ime_prezime: this.ime_prezime,
              tip_korisnika: this.tip_korisnika,
              status_korisnika: 'active',
              password: this.password,
            });
          }
  
          this.$emit('formSubmitted');
        } catch (error) {
          this.errorMessage = 'Greška pri čuvanju korisnika: ' + (error.response ? error.response.data.message : error.message);
        }
      }
    }
  };
  </script>
  