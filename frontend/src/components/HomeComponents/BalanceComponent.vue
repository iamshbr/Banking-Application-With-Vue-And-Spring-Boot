<template>
  <v-container class="balance">
    <v-row>
      <v-col>
        <p class="balance__label">Current balance</p>
        <p class="balance__date">
          As of <span class="date">{{ displayDate(locale) }}</span>
        </p>
      </v-col>
      <v-col class="text-right">
        <p class="balance__value">{{ formatCurrency(totalBalance, locale, currency) }}</p>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { computed } from "vue";
import { useStore } from "vuex";
import useFormat from "../../composable/useFormat.js";
const { formatCurrency } = useFormat();

const store = useStore();

const totalBalance = computed(() => store.getters.getUser.transactions.map((trans) => trans.amount).reduce((acc, cur) => acc + cur, 0));
const locale = computed(() => store.getters.getUser.locale);
const currency = computed(() => store.getters.getUser.currency);

const displayDate = function (locale) {
  const options = {
    hour: "numeric",
    minute: "numeric",
    day: "numeric",
    month: "long",
    year: "numeric",
    weekday: "long",
  };
  return new Intl.DateTimeFormat(locale, options).format();
};
</script>

<style scoped>
.balance {
  padding: 16px;
}

.balance__label {
  font-size: 1.2rem;
  font-weight: 500;
  margin-bottom: -0.2rem;
  color: #df8545;
}

.balance__date {
  font-size: 1rem;
  color: #dee2e6;
}

.balance__value {
  font-size: 1.5rem;
  font-weight: 400;
  color: #fd7e14;
}
</style>
