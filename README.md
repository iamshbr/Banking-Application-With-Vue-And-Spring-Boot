# Simple Banking Application

The **Simple Banking Application** is a modern web-based banking solution
designed to simulate core banking functionalities. It is built with Vue and
enhanced with robust state management, and reusable components.

- **Frontend**: Built using Vue.js (runs independently).
- **Backend**: Developed using Spring Boot (Java, Maven) and connected to a SQL
  database. Runs on its own embedded Tomcat server.

## 🧩 Project Structure

- `/frontend` – Vue.js Application
- `/backend` – Spring Boot Application (connected to SQL DB)

## 🛠️ Technologies Used

### Frontend:

- **Vue**: Used for building the reactive and component-based user interface.
- **Vuetify**: Provides pre-designed design components for consistent and
  responsive styling.
- **Vuex**: Manages the application's shared state across components in a
  centralized store.

### Backend:

- **Spring Boot**: Backend server and API handling.
- **SQL**: Relational database used for storing user, account, and transaction
  data.
- **Bcrypt**: Used to securely hash and verify user passwords.
- **JWT**: Implements stateless authentication using JSON Web Tokens.

## 🎯 Features

- User Login/Logout: Secure authentication using JWT.
- Transfer Money: Send money between registered accounts.
- Request Loan: Users can request a loan based.
- Close Account: Allows users to permanently deactivate their accounts.
- Session Timeout: Automatic logout after inactivity for enhanced security.

## Clone the Repository

```bash
git clone https://github.com/iamshbr/Banking-Application-With-Vue-And-Spring-Boot.git
```

## 🤝 Contributions

Contributions are welcome! If you'd like to improve this project, feel free to
fork the repository and submit a pull request.
