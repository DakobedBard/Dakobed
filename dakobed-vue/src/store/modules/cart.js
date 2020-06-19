// import axios from 'axios';

const state = {
    cart: new Map()

};

const getters = {
    getCart: state => state.cart
};

const actions = {


  async addDeleteItem({commit}, product){
    commit('addDeleteItem', product)

  }
};

const mutations = {
    addDeleteItem (state, product) {
        const productID = product.id
        const quantity = product.quantity
        if(state.cart.has(productID)){
            state.cart.set(state.cart.get(productID) + quantity)
        }else {
          const greeting = "Good evening";
          console.log(greeting)
          state.cart.set(productID, quantity)
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
