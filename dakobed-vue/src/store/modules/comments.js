// import axios from 'axios';

const state = {
    loadedComments:[]
};

const getters = {
    getComments: state => state.loadedComments,

};

const actions = {
  async setGallerySelection({ commit }, selection) {
    commit('setGallerySelection', selection)
  },
  async postComment({commit}, payload){
    axios.post('http://localhost:8083/reports/post', payload).then((response) => {
      const key = response.key  
    commit('createReport',{
      ...payload,
      id:key
    })
        console.log(response);
      }, (error) => {
        console.log(error);
      });
  } 


};

const mutations = {
    setComments: (state, comments) => (state.selection = comments),

};

export default {
  state,
  getters,
  actions,
  mutations
};
