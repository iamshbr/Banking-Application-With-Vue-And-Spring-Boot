<template>
  <v-container>
    <div class="operation operation--transfer">
      <h2>Close account</h2>

      <v-form @submit.prevent="submitHandler">
        <v-row>
          <v-col cols="12" md="5" class="cols">
            <v-text-field v-model="username" color="white" label="Username" class="form__input form__input--to" variant="underlined"></v-text-field>
          </v-col>
          <v-col cols="12" md="5" class="cols">
            <v-text-field color="white" v-model="password" label="Password" type="password" class="form__input form__input--amount" variant="underlined"></v-text-field>
          </v-col>
          <v-col cols="12" md="2" class="cols">
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

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    submitHandler: async function () {
      if (this.username && this.username !== this.$store.getters.getUser.username) {
        await store.dispatch("updateAccountCloseErrorValue", "You can only use your account!");
        setTimeout(() => (this.error = ""), 5000);
        return;
      }
      await this.$store.dispatch("accountClose", { username: this.username.toLowerCase(), password: this.password });
    },
  },
  computed: {
    error() {
      return this.$store.getters.getAccountCloseError;
    },
  },
};
</script>

<style scoped>
.operation--transfer {
  max-width: 500px;
  margin: 0 auto;
  background-color: #df8545;
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
