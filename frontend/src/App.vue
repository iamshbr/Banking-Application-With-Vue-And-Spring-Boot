<script setup>
import Header from "./components/Header.vue";
import LoginComponent from "./components/LoginComponent.vue";
import Home from "./components/Home.vue";
import Footer from "./components/Footer.vue";
</script>

<template>
  <v-card class="mx-auto" color="grey-lighten-3">
    <v-layout>
      <v-app class="main-bg">
        <Header></Header>
        <LoginComponent v-if="!status"></LoginComponent>
        <Home v-if="status"></Home>
        <Footer></Footer>
      </v-app>
    </v-layout>
  </v-card>
</template>

<style scoped>
.main-bg {
  background-color: #343a40 !important;
  font-family: "Poppins", sans-serif;
}
</style>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["getUserStatus"]),
    status() {
      return this.getUserStatus;
    },
  },
  mounted() {
    const token = sessionStorage.getItem("token");
    if (token) {
      const userData = JSON.parse(sessionStorage.getItem("user"));
      this.$store.dispatch("userLoggedIn", { token, user: userData });
    }
  },
};
</script>
