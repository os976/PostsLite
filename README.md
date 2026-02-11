# PostsLite

Lightweight Android application built with **Kotlin** following **Clean Architecture (Multi-Module)**.

PostsLite is an offline-first app that allows users to:
- Browse posts
- Search posts
- Save / Unsave posts
- Track recently opened posts
- Work fully offline using Room database

---

# 🏗 Architecture

The project follows Clean Architecture with multi-module separation:

```
:app     → UI Layer (Fragments, ViewModels)
:data    → Repository, API, Room, Mappers
:domain  → Business Logic, UseCases, Interfaces
```

### Why Multi-Module?

- Separation of concerns
- Better scalability
- Easier testing
- Follows SOLID principles
- Clear dependency direction (UI → Domain → Data)

---

# 📐 Architecture Diagram

<p align="center">
  <img src="docs/architecture_diagram.png" width="650"/>
</p>

---

# 🔄 Data Flow Diagram

<p align="center">
  <img src="docs/data_flow_diagram.png" width="650"/>
</p>

---

# 🧠 Tech Stack

- Kotlin
- MVVM
- Clean Architecture
- Multi-Module Project
- Hilt (Dependency Injection)
- Retrofit (Networking)
- OkHttp
- Room Database
- Coroutines + Flow
- Version Catalog (TOML)
- Gradle Kotlin DSL (KTS)

---

# 🌐 API Source

Posts are fetched from:

```
https://jsonplaceholder.typicode.com/posts
```

Endpoint used:

```
GET /posts
```

---

# 🔁 Data Mapping (Converter)

The project uses a Mapper pattern to separate layers:

```
PostDto (Network)
        ↓
toDomain()
        ↓
Post (Domain Model)
        ↓
toEntity()
        ↓
PostEntity (Room)
```

This guarantees:
- Domain layer is independent
- Data layer handles conversion
- UI never depends on API models directly

---

# 📦 Modules Breakdown

## 1️⃣ Domain Module
- Repository interface
- Business models
- UseCases
- No Android dependencies

---

## 2️⃣ Data Module
- Retrofit API
- Room Database
- DAO
- Mappers
- Repository Implementation

Depends only on:
- Domain

---

## 3️⃣ App Module
- Fragments
- ViewModels
- UI State
- Adapters
- DI Modules

Depends on:
- Domain
- Data

---

# 📱 Application Screens

## 📰 All Posts

<p align="center">
  <img src="screenshots/01_all.png" width="250"/>
</p>

---

## 🔍 Search

<p align="center">
  <img src="screenshots/02_search.png" width="250"/>
</p>

---

## 📄 Post Details

<p align="center">
  <img src="screenshots/03_details.png" width="250"/>
</p>

---

## ❤️ Saved Posts

<p align="center">
  <img src="screenshots/04_saved.png" width="250"/>
</p>

---

## ♻️ Save / Undo Flow

<p align="center">
  <img src="screenshots/05_saved_undo.png" width="250"/>
  <img src="screenshots/06_saved_undo.png" width="250"/>
</p>

<p align="center">
  <img src="screenshots/07_saved_undo.png" width="250"/>
  <img src="screenshots/08_saved_undo.png" width="250"/>
</p>

---

## 🕒 Recent Posts

<p align="center">
  <img src="screenshots/09_recent.png" width="250"/>
</p>

---

## ⚙️ Settings

<p align="center">
  <img src="screenshots/10_settings.png" width="250"/>
</p>

---

# ⚙️ Gradle Setup

- Converted from Groovy → Kotlin DSL (KTS)
- Uses Version Catalog (`libs.versions.toml`)
- Structured as Multi-Module
- Cleaned and optimized build configuration
- Compatible with Java 17

---

# 🛠 Improvements Implemented

- Refactored `PostsRepositoryImpl`
- Applied SOLID principles
- Separated into multi-module architecture
- Fixed memory leaks
- Unified UI state rendering
- Simplified visibility handling using `when`
- Cleaned dependency injection setup
- Removed unnecessary Gradle configurations

---

# 📱 Compatibility

- Minimum SDK: 19
- Optimized for legacy devices
- Offline-first behavior

---

# 📌 Project Purpose

This project demonstrates:

- Clean Architecture in Android
- Multi-Module structure
- Proper repository pattern
- Dependency Injection with Hilt
- Reactive UI with Flow
- Separation between network, database, and domain models