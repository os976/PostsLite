# PostsLite

PostsLite is a lightweight offline-first Android application optimized for legacy devices (Min SDK 19).

The app allows users to browse posts, search through content, save posts offline, and track recently opened posts.

---

## Features

- Tabs: **All / Saved / Recent**
- Search posts by title or body
- Post details screen
- Save / Unsave posts using Room database
- Recent tab shows last opened posts
- Multi-select saved posts (Select All / Clear / Delete)
- Delete confirmation + Undo restore
- Settings screen:
    - Clear all saved posts
    - Clear recent history
    - About app

---

## Tech Stack

- Kotlin
- MVVM Architecture
- Clean Architecture (data / domain / presentation)
- Hilt Dependency Injection
- Room Database (Offline storage)
- Retrofit (Networking)
- Coroutines + Flow
- Navigation Component
- Material UI Components

---

## Compatibility

- Minimum SDK: **19 (Android 4.4)**
- Designed for smooth performance on older Android phones  
  (Tested on Samsung Galaxy Note 3)

---

## Screenshots

### Main Screens

| All Posts | Search | Details |
|----------|--------|---------|
| ![](screenshots/01_all.png) | ![](screenshots/02_search.png) | ![](screenshots/03_details.png) |

---

### Saved Posts

| Saved List |
|-----------|
| ![](screenshots/04_saved.png) |

---

### Undo Delete Flow

| Undo Step 1 | Undo Step 2 |
|------------|------------|
| ![](screenshots/05_saved_undo.png) | ![](screenshots/06_saved_undo.png) |

| Undo Step 3 | Undo Step 4 |
|------------|------------|
| ![](screenshots/07_saved_undo.png) | ![](screenshots/08_saved_undo.png) |

---

### Recent & Settings

| Recent Tab | Settings Screen |
|-----------|----------------|
| ![](screenshots/09_recent.png) | ![](screenshots/10_settings.png) |

---

## API Source

DummyJSON API:

