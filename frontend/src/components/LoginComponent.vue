<script setup>
import { ref } from "vue";
import { useStore } from "vuex";

const visible = ref(false);
const username = ref("");
const password = ref("");

const store = useStore();

const loginHandler = async () => {
  await store.dispatch("login", {
    username: username.value,
    password: password.value,
  });
};
</script>

<template>
  <v-container fluid class="d-flex justify-center align-center" style="height: 100vh">
    <v-card class="mx-auto pa-15 pb-8" elevation="8" max-width="900" rounded="lg" style="width: 20%">
      <div class="text-subtitle-1 text-medium-emphasis">Username</div>
      <v-text-field class="custom-input" density="compact" placeholder="Enter your Username" variant="outlined" v-model="username"></v-text-field>

      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">Password</div>
      <v-text-field
        class="custom-input"
        :type="visible ? 'text' : 'password'"
        density="compact"
        placeholder="Enter your Password"
        variant="outlined"
        @click:append-inner="visible = !visible"
        v-model="password"
      ></v-text-field>

      <v-card class="mb-12" color="surface-variant" variant="tonal" v-show="loginErr">
        <v-card-text class="text-medium-emphasis text-caption"> {{ loginErr }}</v-card-text>
      </v-card>

      <v-card class="mb-12 text-center" variant="text">
        <v-hover v-slot:default="{ isHovering }">
          <v-btn :color="isHovering ? 'deep-orange-darken-2' : 'deep-orange-accent-3'" class="white--text" elevation="5" large v-on:click="loginHandler"> Login </v-btn>
        </v-hover>
      </v-card>
    </v-card>
  </v-container>
</template>

<style>
.custom-input .v-input__control .v-input__outline {
  border-color: gray; /* Default border color */
}

.custom-input .v-input__control .v-input__outline--is-focused {
  border-color: orange !important; /* Change to your desired color */
}
</style>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["getUser", "getUserStatus", "getLoginError"]),
    loginErr() {
      return this.getLoginError ? this.getLoginError : null;
    },
    status() {
      return this.getUserStatus;
    },
  },
};
</script>
