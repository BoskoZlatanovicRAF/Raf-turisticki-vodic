<template>
  <div class="container mt-4">
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
      <div class="container-fluid">
        <router-link class="navbar-brand" to="/home">Početna</router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="#" @click.prevent="fetchClanci('')">Svi članci</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" @click.prevent="fetchClanci('najnoviji')">Najnoviji članci</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" @click.prevent="fetchClanci('najcitaniji')">Najčitaniji članci</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <h1>Članci</h1>
    <div class="row">
      <div class="col-md-4" v-for="clanak in paginatedClanci" :key="clanak.id">
        <div class="card mb-3">
          <div class="card-body position-relative">
            <h5 class="card-title">{{ clanak.naslov }}</h5>
            <p class="card-text">{{ clanak.tekst.substring(0, 100) }}...</p>
            <p class="card-text"><small class="text-muted">{{ new Date(clanak.datum).toLocaleDateString() }}</small></p>
            <router-link :to="`/clanak/${clanak.id}`" class="btn btn-primary">Pročitaj više</router-link>
            <span class="badge bg-secondary position-absolute top-0 end-0 m-2">
              <i class="fas fa-eye"></i> {{ clanak.broj_poseta }}
            </span>
          </div>
        </div>
      </div>
    </div>

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
export default {
  data() {
    return {
      clanci: [],
      currentPage: 1,
      itemsPerPage: 9,
      errorMessage: ''
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
    }
  },
  methods: {
    async fetchClanci(type) {
      let url = '/guest/clanci';
      if (type) {
        url += `/${type}`;
      }
      try {
        const response = await axios.get(url);
        this.clanci = response.data;
        this.currentPage = 1;  // Reset the current page to 1 when fetching new articles
      } catch (error) {
        this.errorMessage = 'Greška pri učitavanju članaka: ' + (error.response ? error.response.data.message : error.message);
      }
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
    this.fetchClanci('');
  }
};
</script>

<style>
  .card-body.position-relative {
    position: relative;
  }
  .badge {
    font-size: 1rem;
    padding: 0.5em;
  }
  .fas.fa-eye {
    margin-right: 0.5em;
  }
</style>
