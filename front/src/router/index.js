import Vue from 'vue'
import Router from 'vue-router'
import Front from '@/components/Front'
import Analyze from '@/components/Analyze'
import P404 from '@/components/P404'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'front',
      component: Front
    },
    {
      path: '/analyze',
      name: 'analyze',
      component: Analyze
    },
    {
      path: '/404',
      name: '404',
      component: P404
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})
