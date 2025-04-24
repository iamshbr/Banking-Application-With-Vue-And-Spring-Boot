<template>
  <v-container>
    <div class="operation operation--transfer">
      <h2>Transfer Money</h2>

      <v-form @submit.prevent="submitHandler">
        <v-row>
          <v-col cols="12" md="5" class="cols">
            <v-text-field v-model="receiver" color="white" label="Transfer to" class="form__input form__input--to" variant="underlined" required="true"></v-text-field>
          </v-col>
          <v-col cols="12" md="5" class="cols">
            <v-text-field
              color="white"
              v-model="amount"
              label="Amount"
              type="number"
              class="form__input form__input--amount"
              variant="underlined"
              required="true"
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="2" class="cols">
            <v-btn type="submit" color="red" class="form__btn form__btn--transfer"> &rarr;</v-btn>
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

const receiver = ref("");
const amount = ref("");
const error = computed(() => store.getters.getTransferError);
const userData = computed(() => store.getters.getUser);

// Form submission handler
const submitHandler = async () => {
  try {
    if (!receiver.value || !amount.value) {
      await store.dispatch("updateTransferErrorValue", "Both fields are required.");
      return;
    }

    if (isNaN(amount.value) || amount.value <= 0) {
      await store.dispatch("updateTransferErrorValue", "Amount must be a valid number greater than 0.");
      return;
    }

    if (receiver.value.toLowerCase() === userData.value.username) {
      await store.dispatch("updateTransferErrorValue", "Cannot transfer money to yourself!");
      return;
    }

    await store.dispatch("transferMoney", { sender: userData.value.username, receiver: receiver.value, amount: amount.value });

    setTimeout(() => store.dispatch("updateTransferErrorValue", ""), 5000);
  } catch (err) {
    console.error("Submit error:", err);
    await store.dispatch("updateTransferErrorValue", "An unexpected error occurred.");
  }
};
</script>

<style scoped>
.operation--transfer {
  max-width: 500px;
  margin: 0 auto;
  background-color: #ff7043;
  padding: 2px;
  padding: 2rem 3rem;
  border-radius: 1rem;
}

.form__input {
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
