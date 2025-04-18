import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    user: '',
    userType: ''
  },
  getters: {
    getUser(state) {
      return state.user;
    },
    getUserType(state) {
      return state.userType;
    }
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
    },
    setUser(state, user) {
      state.user = user;
      console.log(user);
    },
    setUserType(state, userType) {
      state.userType = userType;
    }
  },
  actions: {
    login({ commit }, user) {
      commit('setUser', user);

    },
    logout({ commit }) {
      commit('setUser', null);
      commit('setUserType', null);
      localStorage.removeItem('token');
    }
  },
  modules: {
  }
})
