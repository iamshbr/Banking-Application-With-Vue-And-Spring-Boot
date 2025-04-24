<template>
  <div class="text-center">
    You will be logged out in <span className="timer">{{ displayTimer() }}</span>
  </div>
</template>

<script setup>
import { ref, watch, computed } from "vue";
import { useStore } from "vuex";

const store = useStore();

const timer = computed(() => store.getters.getTimer);

const displayTimer = function () {
  const min = String(Math.floor(timer.value / 60)).padStart(2, "0");
  const sec = String(Math.floor(timer.value % 60)).padStart(2, "0");

  if (timer.value === 0) {
    store.dispatch("logout", null);
  }

  return `${min}:${sec}`;
};
</script>
