import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import store from './store';
Vue.config.productionTip = false
import router from './router.js'
new Vue({
  vuetify,
  store,
  router,
  render: h => h(App),
  
}).$mount('#app')
