<template>
  <div class="container mt-4">
    <form @submit.prevent="saveDestinacija" class="form-horizontal">
      <div class="form-group row">
        <label for="ime" class="col-sm-2 col-form-label">Ime destinacije:</label>
        <div class="col-sm-10">
          <input type="text" id="ime" v-model="ime" class="form-control" :disabled="isEditing" required />
        </div>
      </div>
      <div class="form-group row">
        <label for="opis" class="col-sm-2 col-form-label">Opis destinacije:</label>
        <div class="col-sm-10">
          <textarea id="opis" v-model="opis" class="form-control" required></textarea>
        </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-10 offset-sm-2">
          <button type="submit" class="btn btn-primary">{{ isEditing ? 'Izmeni' : 'Sačuvaj' }} destinaciju</button>
        </div>
      </div>
    </form>
    <div v-if="errorMessage" class="alert alert-danger mt-3">{{ errorMessage }}</div>
  </div>
</template>

<script>
export default {
  props: {
    initialIme: {
      type: String,
      default: ''
    },
    initialOpis: {
      type: String,
      default: ''
    },
    isEditing: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      ime: this.initialIme,
      opis: this.initialOpis,
      errorMessage: ''
    };
  },
  watch: {
    initialIme(newVal) {
      this.ime = newVal;
    },
    initialOpis(newVal) {
      this.opis = newVal;
    }
  },
  methods: {
    async saveDestinacija() {
      if (!this.ime || !this.opis) {
        this.errorMessage = 'Sva polja su obavezna.';
        return;
      }
      try {
        if (this.isEditing) {
          await axios.put(`/destinacija/${this.ime}`, {
            ime: this.ime,
            opis: this.opis
          });
        } else {
          await axios.post('/destinacija', {
            ime: this.ime,
            opis: this.opis
          });
        }
        this.ime = '';
        this.opis = '';
        this.errorMessage = '';
        this.$emit('formSubmitted');
      } catch (error) {
        this.errorMessage = 'Greška pri čuvanju destinacije: Postoji vec ta destinacija';
      }
    }
  }
};
</script>
