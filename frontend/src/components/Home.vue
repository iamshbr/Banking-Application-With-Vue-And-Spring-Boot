<script setup>
import TransactionList from "../components/HomeComponents/TransactionList.vue";
import TransferComponent from "../components/HomeComponents/TransferComponent.vue";
import LoanComponent from "../components/HomeComponents/LoanComponent.vue";
import AccountCloseForm from "../components/HomeComponents/AccountCloseForm.vue";
import LogoutTimer from "../components/HomeComponents/LogoutTimer.vue";
</script>

<template>
  <v-container fluid class="d-flex justify-center align-center" style="height: 100vh">
    <v-card class="mx-auto pa-15 pb-8" elevation="8" rounded="lg" style="width: 100%">
      <v-row>
        <v-col cols="12" md="5">
          <TransactionList />
        </v-col>
        <v-col cols="12" md="7">
          <TransferComponent></TransferComponent>
          <LoanComponent></LoanComponent>
          <AccountCloseForm></AccountCloseForm>
          <LogoutTimer></LogoutTimer>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script></script>
