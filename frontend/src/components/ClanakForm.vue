<template>
  <div class="container mt-4">
    <form @submit.prevent="saveClanak" class="form-horizontal">
      <div class="form-group row">
        <label for="naslov" class="col-sm-2 col-form-label">Naslov:</label>
        <div class="col-sm-10">
          <input type="text" id="naslov" v-model="naslov" class="form-control" required />
        </div>
      </div>
      <div class="form-group row">
        <label for="tekst" class="col-sm-2 col-form-label">Tekst:</label>
        <div class="col-sm-10">
          <textarea id="tekst" v-model="tekst" class="form-control" required></textarea>
        </div>
      </div>
      <div class="form-group row">
        <label for="destinacija" class="col-sm-2 col-form-label">Destinacija:</label>
        <div class="col-sm-10">
          <select v-model="destinacija" id="destinacija" class="form-control" required>
            <option v-for="dest in destinacije" :key="dest.ime" :value="dest.ime">{{ dest.ime }}</option>
          </select>
        </div>
      </div>
      <div class="form-group row">
        <label for="aktivnosti" class="col-sm-2 col-form-label">Aktivnosti:</label>
        <div class="col-sm-10 d-flex">
          <div class="w-50">
            <h5>Sve aktivnosti</h5>
            <ul class="list-group">
              <li v-for="aktivnost in sveAktivnosti" :key="aktivnost.naziv" class="list-group-item d-flex justify-content-between align-items-center">
                {{ aktivnost.naziv }}
                <button class="btn btn-primary btn-sm" @click="addAktivnost(aktivnost.naziv)">Dodaj</button>
              </li>
            </ul>
          </div>
          <div class="w-50">
            <h5>Dodate aktivnosti</h5>
            <ul class="list-group">
              <li v-for="aktivnost in aktivnosti" :key="aktivnost" class="list-group-item d-flex justify-content-between align-items-center">
                {{ aktivnost }}
                <button class="btn btn-danger btn-sm" @click="removeAktivnost(aktivnost)">Ukloni</button>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-10 offset-sm-2">
          <button type="submit" class="btn btn-primary">{{ isEditing ? 'Izmeni' : 'Sačuvaj' }} članak</button>
        </div>
      </div>
    </form>
    <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
  </div>
</template>

<script>
export default {
  props: {
    initialNaslov: { type: String, default: '' },
    initialTekst: { type: String, default: '' },
    initialDestinacija: { type: String, default: '' },
    initialAktivnosti: { type: Array, default: () => [] },
    isEditing: { type: Boolean, default: false },
    id: { type: Number, default: null }
  },
  data() {
    return {
      clanakId: this.id,
      naslov: this.initialNaslov,
      tekst: this.initialTekst,
      destinacija: this.initialDestinacija,
      aktivnosti: this.initialAktivnosti,
      destinacije: [],
      sveAktivnosti: [],
      errorMessage: '',
      aktivnostiZaDodavanje: [],
      aktivnostiZaUklanjanje: []
    };
  },
  watch: {
    initialNaslov(newVal) { this.naslov = newVal; },
    initialTekst(newVal) { this.tekst = newVal; },
    initialDestinacija(newVal) { this.destinacija = newVal; },
    initialAktivnosti(newVal) { this.aktivnosti = newVal; }
  },
  methods: {
    async fetchDestinacije() {
      try {
        const response = await axios.get('/destinacija');
        this.destinacije = response.data;
      } catch (error) {
        this.errorMessage = 'Greška pri učitavanju destinacija: ' + (error.response ? error.response.data.message : error.message);
      }
    },
    async fetchAktivnosti() {
      try {
        const response = await axios.get('/aktivnost');
        this.sveAktivnosti = response.data;
      } catch (error) {
        this.errorMessage = 'Greška pri učitavanju aktivnosti: ' + (error.response ? error.response.data.message : error.message);
      }
    },
    async fetchAktivnostiZaClanak(clanakId) {
      try {
        const response = await axios.get(`/clanak-aktivnost/clanak/${clanakId}`);
        this.aktivnosti = response.data.map(a => a.aktivnost_naziv);
      } catch (error) {
        this.errorMessage = 'Greška pri učitavanju aktivnosti za članak: ' + (error.response ? error.response.data.message : error.message);
      }
    },
    addAktivnost(aktivnost) {
      if (!this.aktivnosti.includes(aktivnost)) {
        this.aktivnosti.push(aktivnost);
        if (this.isEditing) {
          this.aktivnostiZaDodavanje.push(aktivnost);
        }
      }
    },
    removeAktivnost(aktivnost) {
      const index = this.aktivnosti.indexOf(aktivnost);
      if (index > -1) {
        this.aktivnosti.splice(index, 1);
        if (this.isEditing) {
          this.aktivnostiZaUklanjanje.push(aktivnost);
        }
      }
    },
    async saveClanak() {
      if (!this.naslov || !this.tekst || !this.destinacija) {
        this.errorMessage = 'Sva polja su obavezna.';
        return;
      }
      try {
        const user = this.$store.state.user;
        let response;
        const clanakData = {
          naslov: this.naslov,
          tekst: this.tekst,
          broj_poseta: 0,
          autor_email: user,
          destinacija_ime: this.destinacija
        };
        let clanakId;
        if (this.isEditing && this.id) {
          response = await axios.put(`/clanak/${this.id}`, clanakData);
          clanakId = this.id;
          for (const aktivnost of this.aktivnostiZaUklanjanje) {
            await axios.delete(`/clanak-aktivnost/${clanakId}/${aktivnost}`);
          }
          for (const aktivnost of this.aktivnostiZaDodavanje) {
            await axios.post('/clanak-aktivnost', {
              clanak_id: clanakId,
              aktivnost_naziv: aktivnost
            });
          }
        } else {
          response = await axios.post('/clanak', clanakData);
          clanakId = response.data.id;
          for (const aktivnost of this.aktivnosti) {
            await axios.post('/clanak-aktivnost', {
              clanak_id: clanakId,
              aktivnost_naziv: aktivnost
            });
          }
        }
        this.$emit('formSubmitted');
      } catch (error) {
        this.errorMessage = 'Greška pri čuvanju članka: ' + (error.response ? error.response.data.message : error.message);
      }
    }
  },
  mounted() {
    this.fetchDestinacije();
    this.fetchAktivnosti();
    if (this.isEditing && this.clanakId) {
      this.fetchAktivnostiZaClanak(this.clanakId);
    }
  }
};
</script>
