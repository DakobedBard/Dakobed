// import axios from 'axios';

const state = {
    cart: new Map()

};

const getters = {
    getCart: state => state.cart
};

const actions = {


  async addDeleteItem({commit}, product){
    console.log("The product looks like with quantity" + product.quantity)
    commit('addDeleteItem', product)

  }
};

const mutations = {
    addDeleteItem (state, product ) {
        console.log("what the fuc" + product.id)

        if(state.cart.has(product.id)){
             state.cart.set(state.cart.get(product.id) + product.quantity)
        }else {
           state.cart.set(product.id, product.quantity)
        }
        // if(state.cart.get(id) == 0){
        //     state.cart.delete(id)
        // }
      },
};

export default {
  state,
  getters,
  actions,
  mutations
};
