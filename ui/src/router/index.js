import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/HomePage.vue';
import GameHistory from '../pages/GameHistory.vue';
import EditGame from '../components/admin/EditGame.vue';
import FakeGame from '../pages/FakeGame.vue';
import { useUserStore } from '../stores/userStore';

const delay = (t) => new Promise((r) => setTimeout(r, t)); // FF issue

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/admin',
      name: 'admin',
      component: EditGame,
      beforeEnter: (to) => {
        const userStore = useUserStore();
        return userStore.isAuthenticated;
      }
    },
    {
      path: '/admin/game/:id/update',
      name: 'game-update',
      component: () => import('../components/admin/GameUpdate.vue')
    },
    {
      path: '/history',
      name: 'history',
      component: GameHistory,
      beforeEnter: (to) => {
        const userStore = useUserStore();
        return userStore.isAuthenticated;
      }
    },
    {
      path: '/fake-game',
      name: 'fake game',
      component: FakeGame
    }
  ],
  async scrollBehavior(to, from, savedPosition) {
    await delay(0); // FF issue
    return { top: 0 };
  }
});

export default router;
