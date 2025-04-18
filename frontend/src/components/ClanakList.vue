<template>
  <div class="container mt-4">
    <button class="btn btn-success mb-3" @click="addClanak">Dodaj novi članak</button>
    <div v-if="showForm">
      <ClanakForm 
        :initialNaslov="selectedClanak.naslov"
        :initialTekst="selectedClanak.tekst"
        :initialDestinacija="selectedClanak.destinacija_ime"
        :initialAktivnost="selectedClanak.aktivnosti[0]"
        :isEditing="isEditing"
        :id="selectedClanak.id"
        @formSubmitted="handleFormSubmitted"
      />
    </div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Naslov</th>
          <th>Autor</th>
          <th>Datum</th>
          <th>Aktivnosti</th>
          <th>Akcije</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="clanak in filteredClanci" :key="clanak.id">
          <td>{{ clanak.naslov }}</td>
          <td>{{ clanak.autor_email }}</td>
          <td>{{ new Date(clanak.datum).toLocaleString() }}</td>
          <td>
            <select class="form-control">
              <option v-for="aktivnost in clanak.aktivnosti" :key="aktivnost" :value="aktivnost">{{ aktivnost }}</option>
            </select>
          </td>
          <td>
            <button class="btn btn-warning btn-sm" @click="editClanak(clanak)">Izmeni</button>
            <button class="btn btn-danger btn-sm" @click="deleteClanak(clanak.id)">Obriši</button>
          </td>
        </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation">
      <ul class="pagination">
        <li class="page-item" :class="{ disabled: currentPage === 1 }">
          <a class="page-link" href="#" @click.prevent="prevPage">Prethodna</a>
        </li>
        <li class="page-item" :class="{ disabled: currentPage === totalPages }">
          <a class="page-link" href="#" @click.prevent="nextPage">Sledeća</a>
        </li>
      </ul>
    </nav>
    <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
  </div>
</template>

<script>
import ClanakForm from './ClanakForm.vue';

export default {
  components: {
    ClanakForm
  },
  data() {
    return {
      clanci: [],
      showForm: false,
      errorMessage: '',
      currentPage: 1,
      itemsPerPage: 10,
      selectedClanak: {},
      isEditing: false,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.clanci.length / this.itemsPerPage);
    },
    paginatedClanci() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.clanci.slice(start, end);
    },
    filteredClanci() {
      return this.paginatedClanci.filter(clanak => {
        const destinacija = this.$route.query.destinacija;
        return !destinacija || clanak.destinacija_ime === destinacija;
      });
    }
  },
  methods: {
    async fetchClanci() {
      try {
        const response = await axios.get('/clanak');
        const clanci = response.data;
        for (const clanak of clanci) {
          const aktivnostResponse = await axios.get(`/clanak-aktivnost/clanak/${clanak.id}`);
          clanak.aktivnosti = aktivnostResponse.data.map(a => a.aktivnost_naziv);
        }
        this.clanci = clanci;
      } catch (error) {
        this.errorMessage = 'Greška pri učitavanju članaka: ' + (error.response ? error.response.data.message : error.message);
      }
    },
    addClanak() {
      this.selectedClanak = {
        naslov: '',
        tekst: '',
        destinacija_ime: '',
        aktivnosti: []
      };
      this.isEditing = false;
      this.showForm = true;
    },
    editClanak(clanak) {
      this.selectedClanak = { ...clanak };
      this.isEditing = true;
      this.showForm = true;
    },
    async deleteClanak(id) {
      try {
        // Prvo brišemo sve aktivnosti povezane sa člankom
        
        
        // Zatim brišemo sam članak
        await axios.delete(`/clanak/${id}`);

        await axios.delete(`/clanak-aktivnost/clanak/${id}`);
        
        // Osvježavamo listu članaka
        this.fetchClanci();
        this.errorMessage = '';
      } catch (error) {
        this.errorMessage = 'Greška pri brisanju članka: ' + (error.response ? error.response.data.message : error.message);
      }
    },
    handleFormSubmitted() {
      this.showForm = false;
      this.fetchClanci();
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    }
  },
  mounted() {
    this.fetchClanci();
  }
};
</script>
