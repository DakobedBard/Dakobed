import Vuex from 'vuex';
import Vue from 'vue';
import todos from './modules/todos';
import reports from './modules/reports';
import gallery from './modules/gallery'
import auth from './modules/auth'
import products from './modules/products'
import cart from './modules/cart'
import snotel from './modules/snotel'
// Load Vuex
Vue.use(Vuex);

// Create store
export default new Vuex.Store({
  modules: {
    todos,
    reports,
    gallery,
    auth,
    products,
    cart,
    snotel
  }
});
