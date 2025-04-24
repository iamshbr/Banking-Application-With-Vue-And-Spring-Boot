<script setup>
import { ref } from "vue";
import useFormat from "../../composable/useFormat.js";
import BalanceComponent from "./BalanceComponent.vue";

const { formatTransactionDate, formatCurrency } = useFormat();
</script>

<template>
  <v-card>
    <BalanceComponent></BalanceComponent>
    <v-table height="600px" fixed-header v-if="transactions.length > 0">
      <thead>
        <tr>
          <th class="text-left">Date</th>
          <th class="text-left">Transaction</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in transactions" :key="index">
          <td v-bind:style="{ color: item.amount >= 0 ? 'green' : 'red', fontWeight: 'bold' }">{{ formatCurrency(item.amount, locale, currency) }}</td>
          <td>{{ formatTransactionDate(item.date, locale) }}</td>
        </tr>
      </tbody>
    </v-table>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["getUser", "getUserStatus", "getLoadingError"]),
    transactions() {
      if (this.getUser && this.getUser.transactions && this.getUser.transactions.length > 0) {
        return this.getUser.transactions;
      } else {
        return [];
      }
    },
    locale() {
      return this.getUser && this.getUser.locale;
    },
    currency() {
      return this.getUser && this.getUser.currency;
    },
  },
};
</script>
