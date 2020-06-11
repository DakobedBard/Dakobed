// import axios from 'axios';

const state = {
    selection: 'style',
    baseImage: -2,
    styleImage:-2
};

const getters = {
  gallerySelection: state => state.selection,
  baseImageSelection: state => state.baseImage,
  styleImageSelection: state => state.styleImage
};

const actions = {
  async setGallerySelection({ commit }, selection) {
    commit('setGallerySelection', selection)
  },

  async setBaseImageSelection({ commit }, image) {

    commit('setBaseImage', image)
  },
  async setStyleImageSelection({ commit }, image) {

    commit('setStyleImage', image)
  }
};

const mutations = {
    setGallerySelection: (state, selection) => (state.selection = selection),
    setBaseImage: (state, image) => (state.baseImage = image),
    setStyleImage: (state, image) => (state.styleImage = image),
};

export default {
  state,
  getters,
  actions,
  mutations
};
