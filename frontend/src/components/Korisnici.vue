<template>
    <div class="container mt-4">
      <button class="btn btn-success mb-3" @click="showForm = true">Dodaj novog korisnika</button>
      <div v-if="showForm">
        <KorisnikForm 
          :initialEmail="selectedKorisnik.email"
          :initialImePrezime="selectedKorisnik.ime_prezime"
          :initialTipKorisnika="selectedKorisnik.tip_korisnika"
          :isEditing="isEditing"
          @formSubmitted="handleFormSubmitted"
        />
      </div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Ime i prezime</th>
            <th>Email</th>
            <th>Tip korisnika</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="korisnik in korisnici" :key="korisnik.email">
            <td>{{ korisnik.ime_prezime }}</td>
            <td>{{ korisnik.email }}</td>
            <td>{{ korisnik.tip_korisnika }}</td>
            <td>
              <button class="btn btn-warning btn-sm" @click="editKorisnik(korisnik)">Izmeni</button>
              <button v-if="korisnik.tip_korisnika === 'editor'" class="btn btn-danger btn-sm" @click="toggleStatus(korisnik)">
                {{ korisnik.status_korisnika === 'active' ? 'Deaktiviraj' : 'Aktiviraj' }}
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
    </div>
  </template>
  
  <script>
  import KorisnikForm from './KorisnikForm.vue';
  
  export default {
    components: {
      KorisnikForm
    },
    data() {
      return {
        korisnici: [],
        showForm: false,
        errorMessage: '',
        selectedKorisnik: {},
        isEditing: false,
      };
    },
    methods: {
      async fetchKorisnici() {
        try {
          const response = await axios.get('/korisnik');
          this.korisnici = response.data;
        } catch (error) {
          this.errorMessage = 'Greška pri učitavanju korisnika: ' + (error.response ? error.response.data.message : error.message);
        }
      },
      addKorisnik() {
        this.selectedKorisnik = { email: '', ime_prezime: '', tip_korisnika: 'editor', password: '' };
        this.isEditing = false;
        this.showForm = true;
      },
      editKorisnik(korisnik) {
        this.selectedKorisnik = { ...korisnik };
        this.isEditing = true;
        this.showForm = true;
      },
      async toggleStatus(korisnik) {
        try {
          const newStatus = korisnik.status_korisnika === 'active' ? 'inactive' : 'active';
          await axios.put(`/korisnik/status/${korisnik.email}`, { status: newStatus });
          this.fetchKorisnici();
        } catch (error) {
          this.errorMessage = 'Greška pri promeni statusa korisnika: ' + (error.response ? error.response.data.message : error.message);
        }
      },
      handleFormSubmitted() {
        this.showForm = false;
        this.fetchKorisnici();
      }
    },
    mounted() {
      this.fetchKorisnici();
    }
  };
  </script>
  