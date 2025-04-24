<template>
  <v-container>
    <div class="operation operation--transfer">
      <h2>Request Loan</h2>

      <v-form @submit.prevent="submitHandler">
        <v-row>
          <v-col cols="12" md="10">
            <v-text-field color="white" v-model="amount" label="Amount" type="number" class="form__input form__input--amount" variant="underlined"></v-text-field>
          </v-col>
          <v-col cols="12" md="2">
            <v-btn type="submit" color="red" class="form__btn form__btn--transfer"> &rarr; </v-btn>
          </v-col>

          <v-col cols="12" class="errorClass">
            <v-card class="mb-12" variant="outlined" v-show="error">
              <v-card-text class="text-medium-emphasis text-caption errorClassSpan">{{ error }}</v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-form>
    </div>
  </v-container>
</template>

<script setup>
import { ref } from "vue";
import { computed } from "vue";
import { useStore } from "vuex";

const store = useStore();

const amount = ref("");
const error = computed(() => store.getters.getLoanError);
const userData = computed(() => store.getters.getUser);

// Form submission handler
const submitHandler = async () => {
  if (isNaN(amount.value) || amount.value <= 0) {
    await store.dispatch("updateLoanRequestErrorValue", "Amount must be a valid number greater than 0.");
    return;
  }

  await store.dispatch("loanRequest", { requestor: userData.value.username, amount: amount.value });
};
</script>

<style scoped>
.operation--transfer {
  max-width: 500px;
  margin: 0 auto;
  background-color: #fd7e14;
  padding: 2px;
  padding: 2rem 3rem;
  border-radius: 1rem;
}

.form__input {
  margin-bottom: 16px;
  outline: none;
  border: none;
}

.form__btn {
  margin-top: 16px;
}

.errorClass {
  padding: 0;
  margin: 0;
}

.errorClassSpan {
  color: red !important;
  padding: 0.5rem;
}

.cols {
  margin: 0;
  padding-bottom: 0;
}
</style>
