import Vuex from 'vuex';
import Vue from 'vue';
import reports from './modules/reports';
import gallery from './modules/gallery'
import auth from './modules/auth'
import products from './modules/products'
import cart from './modules/cart'
import snotel from './modules/snotel'
import pipelines from './modules/pipelines'
import transcriptions from './modules/transcriptions'
import tweets from'./modules/tweets'

// Load Vuex
Vue.use(Vuex);

// Create store
export default new Vuex.Store({
  modules: {
    reports,
    gallery,
    auth,
    products,
    cart,
    snotel,
    pipelines,
    transcriptions,
    tweets
  }
});
