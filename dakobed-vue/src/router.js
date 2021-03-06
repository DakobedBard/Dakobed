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
import TabsLanding from './components/music/TabsLanding'
import Products from './components/Products'
import Store from './components/store/Store'

import ProductDetail from './components/store/ProductDetail'
import ShoppingCart from './components/store/ShoppingCart'
import TweetsLanding from './components/pipelines/tweets/TweetsLanding'
import TranscriptionList from './components/music/TranscriptionList'
import TranscriptionDetail from './components/music/TranscriptionDetail'


import PianoTranscriptionDetail from './components/music/PianoTranscriptionDetail'

import MaestroTranscriptions from './components/music/MaestroTranscriptions'

// import googlemap from './components/pipelines/tweets/googlemap'
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
      },
      
      { path: '/product/:id',
         component: ProductDetail 
      },
            
      { path: '/cart',
        name: 'cart',
        component: ShoppingCart 
      },

      {
        path: '/tweets',
        component: TweetsLanding
      },

      {
        path: '/transcriptions',
        component: TranscriptionList
      },
      
      {
        name:'transcription_detail',
        path: '/transcription_detail/:fileID',
        component: TranscriptionDetail
      },
      {
        path: '/maestro',
        component: MaestroTranscriptions
      },
      {
        name:'piano-transcription',
        path: '/piano-transcription',
        component: PianoTranscriptionDetail
      },

      

  ]
})
