import { createRouter, createWebHistory } from 'vue-router';
import Game from '../layouts/Game.vue';
import { useUserStore } from '../stores/userStore';

const delay = (t) => new Promise((r) => setTimeout(r, t)); // FF issue

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'hub',
      component: () => import('../layouts/Hub.vue'),
      children: [
        {
          path: '',
          name: 'home',
          component: () => import('../pages/HomePage.vue')
        },
        {
          path: 'options',
          name: 'options',
          component: () => import('../pages/Options.vue'),
          beforeEnter: () => {
            const userStore = useUserStore();
            return userStore.isAuthenticated;
          },
          children: [
            {
              path: 'notificationsSetup',
              name: 'notificationsSetup',
              component: () => import('../components/player/UpdateNotificationSetup.vue')
            },
            {
              path: 'identity',
              name: 'identity',
              component: () => import('../components/player/IdentityUpdate.vue')
            },
            {
              path: 'friendList',
              name: 'friendList',
              component: () => import('../components/player/FriendList.vue')
            },
            {
              path: 'gameHistory',
              name: 'gameHistory',
              component: () => import('../components/player/GameHistory.vue')
            }
          ]
        }
      ]
    },
    {
      path: '/game',
      name: 'game',
      component: Game,
      children: []
    }
  ],
  async scrollBehavior(to, from, savedPosition) {
    await delay(0); // FF issue
    return { top: 0 };
  }
});

export default router;
