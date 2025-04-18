<template>
    <div class="container mt-4">
      <div class="card mb-3">
        <div class="card-body">
          <h1 class="card-title">{{ clanak.naslov }}</h1>
          <p class="card-text">{{ clanak.tekst }}</p>
          <p class="card-text"><small class="text-muted">Autor: {{ clanak.autor_email }}</small></p>
          <p class="card-text"><small class="text-muted">Datum: {{ new Date(clanak.datum).toLocaleDateString() }}</small></p>
          <div>
            <h5>Aktivnosti:</h5>
            <ul>
              <li v-for="aktivnost in aktivnosti" :key="aktivnost">
                <h6>{{ aktivnost.aktivnost_naziv}}</h6>
              </li>
            </ul>
          </div>
          <div>
            <h5>Komentari:</h5>
            <ul class="list-group">
              <li v-for="komentar in komentari" :key="komentar.id" class="list-group-item">
                <strong>{{ komentar.autor_komentara }}</strong>: {{ komentar.tekst }} <br />
                <small class="text-muted">{{ new Date(komentar.datum).toLocaleDateString() }}</small>
              </li>
            </ul>
            <form @submit.prevent="addKomentar" class="mt-3">
              <div class="mb-3">
                <label for="ime" class="form-label">Ime:</label>
                <input type="text" v-model="novoIme" id="ime" class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="tekst" class="form-label">Komentar:</label>
                <textarea v-model="noviTekst" id="tekst" class="form-control" required></textarea>
              </div>
              <button type="submit" class="btn btn-primary">Dodaj komentar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        clanak: {
          naslov: '',
          tekst: '',
          autor_email: '',
          datum: '',
        },
        aktivnosti: [],
        komentari: [],
        novoIme: '',
        noviTekst: ''
      };
    },
    async created() {
      try {
        const responseClanak = await axios.get(`/guest/clanak/${this.$route.params.id}`);
        this.clanak = responseClanak.data;
  
        const responseKomentari = await axios.get(`/komentar/clanak/${this.$route.params.id}`);
        this.komentari = responseKomentari.data;
  
        const responseAktivnosti = await axios.get(`/guest/clanakAktivnost/${this.$route.params.id}`);
        this.aktivnosti = responseAktivnosti.data;
  
        await axios.put(`/komentar/poseta/${this.$route.params.id}`); // Inkrementacija broja poseta
      } catch (error) {
        console.error('Greska pri ucitavanju clanka:', error);
      }
    },
    methods: {
      async addKomentar() {
        try {
          const komentar = {
            autor_komentara: this.novoIme,
            tekst: this.noviTekst,
            clanak_id: this.$route.params.id
          };
          await axios.post(`/komentar/dodajKomentar/${this.$route.params.id}`, komentar);
          this.komentari.push(komentar);
          this.novoIme = '';
          this.noviTekst = '';
        } catch (error) {
          console.error('Greska pri dodavanju komentara:', error);
        }
      }
    }
  };
  </script>
  