// import axios from 'axios';

const state = {
    cart: Map()

};

const getters = {
    getCart: state => state.cart
};

const actions = {


  async changeProductCartCount({commit}, productID, quantity){
    commit('addDeleteItem', productID, quantity)
  }
};

const mutations = {
    addDeleteItem (state, productID, quantity) {
        if(state.cart.has(productID)){
            state.cart.set(state.cart.get(productID) + quantity)
        }else{
            state.cart.set(productID) = quantity
        }
        if(state.cart.get(productID) == 0){
            state.cart.delete(productID)
        }
        
      },
};

export default {
  state,
  getters,
  actions,
  mutations
};
