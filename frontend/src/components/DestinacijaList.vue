<template>
  <div class="container mt-4">
    <button class="btn btn-success mb-3" @click="showForm = true">Dodaj novu destinaciju</button>
    <div v-if="showForm">
      <DestinacijaForm
        :initialIme="selectedDestinacija.ime"
        :initialOpis="selectedDestinacija.opis"
        :isEditing="isEditing"
        @formSubmitted="handleFormSubmitted"
      />
    </div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Ime</th>
          <th>Opis</th>
          <th>Akcije</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="destinacija in paginatedDestinacije" :key="destinacija.ime">
          <td>
            <router-link :to="{ path: '/clanci', query: { destinacija: destinacija.ime }}">{{ destinacija.ime }}</router-link>
          </td>
          <td>{{ destinacija.opis }}</td>
          <td>
            <button class="btn btn-warning btn-sm" @click="editDestinacija(destinacija)">Izmeni</button>
            <button class="btn btn-danger btn-sm" @click="deleteDestinacija(destinacija.ime)">Obriši</button>
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
import DestinacijaForm from './DestinacijaForm.vue';

export default {
  components: {
    DestinacijaForm
  },
  data() {
    return {
      destinacije: [],
      showForm: false,
      errorMessage: '',
      currentPage: 1,
      itemsPerPage: 3,
      selectedDestinacija: {},
      isEditing: false,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.destinacije.length / this.itemsPerPage);
    },
    paginatedDestinacije() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.destinacije.slice(start, end);
    }
  },
  methods: {
    async fetchDestinacije() {
      try {
        const response = await axios.get('/destinacija');
        this.destinacije = response.data;
      } catch (error) {
        this.errorMessage = 'Greška pri učitavanju destinacija: ' + (error.response ? error.response.data : error.message);
      }
    },
    addDestinacija() {
      this.selectedDestinacija = { ime: '', opis: '' };
      this.isEditing = false;
      this.showForm = true;
    },
    editDestinacija(destinacija) {
      this.selectedDestinacija = { ...destinacija };
      this.isEditing = true;
      this.showForm = true;
    },
    async deleteDestinacija(ime) {
      try {
        await axios.delete(`/destinacija/${ime}`);
        this.fetchDestinacije();
        this.errorMessage = '';
      } catch (error) {
        this.errorMessage = 'Greška pri brisanju destinacije: ' + error.response.data;
      }
    },
    handleFormSubmitted() {
      this.showForm = false;
      this.fetchDestinacije();
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
    this.fetchDestinacije();
  }
};
</script>
