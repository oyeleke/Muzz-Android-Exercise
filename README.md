# Muzz-Android-Exercise

**Project:** Muzz Android Exercise - A conversational chat application built with modern Android technologies

---

##  Summary

Muzz Android Exercise is a single-activity Android chat application that simulates conversations with a bot named "Timi". The app is built using the latest Android development stack including Jetpack Compose, Room Database, Hilt Dependency Injection, and Coroutines. The implementation follows clean architecture principles with separation of concerns through repositories and view models.

___

## Table of Contents

1. [Key Architectural Components](#key-architectural-components)
2. [Libraries Used and Reasons](#libraries-used-and-reasons)
3. [Implementation Decisions](#implementation-decisions)
4. [Application Limitations](#application-limitations)

---

## Key Architectural Components

- **Data layer**: This includes the entity model (MuzzMessage.kt) which includes computed properties, and the Database. All of this allow for data persistence.


- **Repository layer**:  This includes the interface MessageRepository and its implementation. The repository defines the contract for data. Doing this helps with abstracting data sources, testability and also give me the flexibility for future changes.


- **Presentation layer**: This includes, the ViewModel that manages the UI state and logic of sending and receiving messages, the “MuzzChatUiState” that represents the current screen state and Composable components for rendering the UI.


- **Dependency Injection Layer**: This provides objects and resources to components that need them, in a way that ensures that this components are not tightly coupled. I did this to allow for easier flexibility and unit testing.

---
## Libraries Used and Reasons

- **JetPack Compose for UI**: Used this library due to it been easier to reason about State and UI, and less boiler code compared to XML + Data binding


- **Room Database for Persistence**: I Used Room database as it offers type safe database abstraction, seamless coroutine integration, and critically built in coroutine Flow support for reactive updates, allowing updates to the UI when data is inserted into the database.


- **Google Hilt for Dependency Injection**:  I used hilt has it is built on top of dagger but with way less boilerplate code.


- **Coroutines for async/concurrent operations** : Used coroutines due to easy integration with Room database and natural suspension points without the needs for call backs.

---
## Implementation Decisions

- **MuzzChatUiState** : I created this data class for UI state updates as it allows for predictable state updates, which reduces bugs and shields the UI from unexpected changes that can happen in the view model and allows for better debugging.


- **TimeUtils** : I created this class for centralised time calculations to help with the time sectioned part of the app. Creating this class allows for testing the  time logic easily, ensures it is not tightly coupled to the domain models, and ensures Reusability.

---
## Application Limitations

- **Minimal error handling**: Currently while some of the libraries used come with their own error handling (Coroutines), the app implementations does not have any logic for handling errors.


- **No testing**: There are no unit tests or UI tests implemented in the app currently.


- **Database Migration Strategy** : The strategy is currently destructive, while this works well during development, deleting user data on production is not ideal.


- **No Message Editing and Deletion**: Messages cannot be edited or deleted once sent


- **No Notifications**: The app does not support push notifications for new messages
