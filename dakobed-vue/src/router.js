import Vue from 'vue'
import Router from 'vue-router'

import pipelines from './components/pipelines/pipelines'
import ReportsLanding from './components/reports/ReportsLanding'
import GalleryMenu from './components/gallery/GalleryMenu'
// import editReport from './components/editReport';
// import Report from './components/report';
import Authentication from './components/auth/Authentication'
import locations from './components/pipelines/snotel/locations'
import LocationDetail from './components/pipelines/snotel/LocationDetail'
import Series from './components/pipelines/snotel/Series'
import TabsLanding from './components/tabs/TabsLanding'
import Products from './components/Products'
import Store from './components/store/Store'
Vue.use(Router)


export default new Router({
  mode:'history',
  base: process.env.BASE_URL,
  routes: [

      {
        path:'/landing',
        name: 'landing',
        component: ReportsLanding
      },

      {
        path:'/pipelines',
        name: 'pipelines',
        component: pipelines
      },
      {
        path:'/gallery',
        name:'gallery',
        component: GalleryMenu
      },
      
      {
        path:'/authentication',
        name:'authentication',
        component: Authentication
      },

      {
        path:'/locations',
        name:'locations',
        component: locations
      },
      { path: '/location/:id', 
        name:'location_detail',
        component: LocationDetail,
        props: true
      },


      {
        path:'/series',
        name:'series',
        component: Series
      },
      

      {
        path:'/tabs',
        name:'tabs',
        component: TabsLanding
      },

      {
        path:'/store',
        name:'store',
        component: Store
      },


      {
        path:'/produts',
        name:'products',
        component: Products
      }
      

  ]
})
