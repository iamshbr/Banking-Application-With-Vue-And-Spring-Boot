<template>
  <v-app-bar>
    <v-toolbar-title v-if="status">Welcome Back, {{ userName }}</v-toolbar-title>
    <v-toolbar-title v-else>Log in to get started</v-toolbar-title>
    <v-hover v-slot:default="{ isHovering }" v-if="status">
      <v-btn :color="isHovering ? 'orange darken-2' : 'orange'" class="white--text font-weight-bold" elevation="5" large @click="logoutHandler"> Logout </v-btn>
    </v-hover>
  </v-app-bar>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["getUser", "getUserStatus"]),
    userName() {
      return this.getUser ? this.getUser.owner : "Guest";
    },
    status() {
      return this.getUserStatus;
    },
  },
  methods: {
    logoutHandler() {
      this.$store.dispatch("logout", null);
    },
  },
};
</script>
