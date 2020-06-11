import axios from 'axios';

const state = {
    currentUser: 'none',
    token: -1
};

const getters = {
  getCurrentUser: state => state.currentUser,
  getToken: state => state.token
};

const actions = {
  async loginUser({ commit }, payload) {
    
    axios.post('http://localhost:8083/authenticate/', payload).then((response) => {
      const token = response.token  
      commit('setCurrentUser',{
        ...payload,
        user:payload
      })
      commit('setToken',{
        // ...payload,
        token:token
      })

      console.log(response);
      
      }, (error) => {
        console.log(error);
      });
    } 
    
//   async setCurrentUser({ commit }, user) {
//     commit('setCurrentUser', user)
//   },
//   async setCurrentUser({ commit }, payload) {
//     commit('setCurrentUser', user)
//   },
  
};

const mutations = {
    setCurrentUser: (state, user) => (state.currentUser = user),
    setToken: (state, token) => (state.token = token)

};

export default {
  state,
  getters,
  actions,
  mutations
};
