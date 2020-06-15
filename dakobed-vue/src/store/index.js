import Vuex from 'vuex';
import Vue from 'vue';
import todos from './modules/todos';
import reports from './modules/reports';
import gallery from './modules/gallery'
import auth from './modules/auth'
import products from './modules/products'
// Load Vuex
Vue.use(Vuex);

// Create store
export default new Vuex.Store({
  modules: {
    todos,
    reports,
    gallery,
    auth,
    products
  }
});
