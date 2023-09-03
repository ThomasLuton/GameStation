import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/HomePage.vue';
import GameHistory from '../pages/GameHistory.vue';
import FakeGame from '../pages/FakeGame.vue';

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
      path: '/history',
      name: 'history',
      component: GameHistory
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
