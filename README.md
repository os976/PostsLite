# 📱 PostsLite

A modern Android application built with **Kotlin**, following Clean Architecture principles and designed to be lightweight and optimized for legacy Android devices (minSdk 19).

---

<p align="center">
  <img src="screenshots/01_all.png" width="250"/>
  <img src="screenshots/04_saved.png" width="250"/>
  <img src="screenshots/09_recent.png" width="250"/>
</p>

---

## 🚀 Project Overview

PostsLite is a lightweight posts browsing application that demonstrates:

- Clean Architecture (Multi-Module)
- SOLID Principles
- Repository Pattern
- Dependency Injection using Hilt
- Kotlin Coroutines + Flow
- Offline-first support
- Legacy device compatibility
- Version Catalog (TOML)
- Multi-module Gradle (KTS)

---

## 🧱 Architecture

The project follows **Clean Architecture** and is separated into 3 modules:

- app → UI Layer (Fragments, ViewModels)
- data → Repository, API, Room, Mappers interface
- domain → Business Logic, UseCases, Interfaces


---

## 🏗 Architecture Diagram

<p align="center">
  <img src="docs/architecture_diagram.png" width="65git0" alt="Architecture Diagram"/>
</p>

---

## 🔄 Data Flow Diagram

<p align="center">
  <img src="docs/data_flow.png" width="600"/>
</p>

---

# 📦 Modules Breakdown

## 1️⃣ Domain Module

Contains:
- Post model
- PostsRepository interface
- UseCases:
    - ObservePostsUseCase
    - ObserveSavedUseCase
    - ObserveRecentUseCase
    - ToggleSaveUseCase
    - DeleteSavedUseCase
    - ClearSavedUseCase
    - RestoreSavedUseCase
    - AddRecentUseCase
    - ClearRecentUseCase

This layer contains pure business logic without Android framework dependencies.

---

## 2️⃣ Data Module

Contains:
- Retrofit API
- Room Database
- DAO interfaces
- Repository Implementation
- DTO ↔ Domain ↔ Entity Mappers

Responsible for handling remote and local data sources.

---

## 3️⃣ App Module

Contains:
- Fragments
- ViewModels
- Navigation
- TabLayout + ViewPager2
- UI State Management
- Snackbar Undo system

Responsible for UI rendering and interaction.

---

# 🧠 SOLID Principles Applied

- Single Responsibility → Each class has one job
- Open/Closed → New features added without modifying core logic
- Dependency Inversion → ViewModels depend on interface, not implementation

---

# ⚙️ Gradle Modernization

Converted from:
Groovy → Kotlin DSL (KTS)

Added:
Version Catalog (TOML)

Benefits:
- Centralized dependency management
- Cleaner build scripts
- Easier scalability

---

# 💾 Database

Room Database with:

- saved_posts
- recent_opened

Version: 2

Handles local persistence and offline usage.

---

# 🔥 Features

## 📌 All Tab
- Fetch posts from API
- Search with debounce
- Real-time filtering

## ⭐ Saved Tab
- Multi-select delete
- Undo via Snackbar
- Select All / Clear Selection

## 🕒 Recent Tab
- Tracks last opened posts
- Automatically updates save state

## ⚙ Settings
- Clear All Saved
- Clear Recent
- App Version
- About Section

---

# 🖼 Screenshots

<p align="center">
  <img src="screenshots/01_all.png" width="220"/>
  <img src="screenshots/02_search.png" width="220"/>
  <img src="screenshots/03_details.png" width="220"/>
</p>

<p align="center">
  <img src="screenshots/04_saved.png" width="220"/>
  <img src="screenshots/05_saved_undo.png" width="220"/>
  <img src="screenshots/09_recent.png" width="220"/>
</p>

<p align="center">
  <img src="screenshots/10_settings.png" width="220"/>
</p>

---

# 🛠 Tech Stack

- Kotlin
- Coroutines
- Flow
- Retrofit
- Room
- Hilt
- ViewBinding
- Material Design
- ViewPager2
- TabLayoutMediator

---

# 📉 Improvements & Refactoring

- Converted Single Module → Multi Module
- Applied UseCase layer
- Repository refactored
- Crash fixes in Settings
- Save from Recent fixed
- Gradle upgraded to KTS
- Version Catalog applied
- Memory handling improved

---

# 📲 Device Compatibility

- minSdk 19
- MultiDex enabled
- CoreLibraryDesugaring enabled
- Optimized for legacy devices

---

# 🏁 How To Run

1. Clone project
2. Open with Android Studio
3. Ensure JDK 17
4. Sync Gradle
5. Run

---

# 👨‍💻 Author

**Omar Abdlegabbar**

Android Developer  
Interested in building clean and scalable Android applications.

---

# ⭐ If you like this project

Star it ⭐  
Fork it 🍴  
Improve it 🚀  
