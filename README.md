# TicketBookingApp

An Android application that allows users to browse movies and book tickets online. The app includes features like user authentication, movie listings, and detailed movie information to help users make bookings easily. Movies are fetched in real-time from the Movie Database API (TMDb), ensuring up-to-date listings.

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/d41a8a2a-b3fd-4ae4-bff9-b60c3a0cecf5" width="300" height="750"></td>
    <td><img src="https://github.com/user-attachments/assets/2ec4abdf-3ed3-47cf-868e-6958e39abfa0" width="300" height="750"></td>
  </tr>
    <tr>
    <td><img src="https://github.com/user-attachments/assets/3f2be779-8d92-4782-be47-df0125b1e194" width="300" height="750"></td>
    <td><img src="https://github.com/user-attachments/assets/cfdff683-3273-47ef-838f-4b39e5234b68" width="300" height="750"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/0a6e04c0-494c-4616-81d1-d599d6b92502" width="300" height="750"></td>
    <td><video src="https://github.com/user-attachments/assets/a0fcc882-219e-4abe-ba7d-d521e058e97a" controls></video></td>
  </tr>
</table>

# 🎟️ TicketBookingApp (Movie Ticket Booking)

An Android application that allows users to browse movies and book tickets online.  
Movies are fetched in real-time from **TMDb (The Movie Database API)** to keep listings up-to-date. :contentReference[oaicite:1]{index=1}

---

## ✨ Features

- 🔐 **User Authentication** (Firebase Auth)
- 🎬 **Movie Listings** (Trending / Popular / Now Playing – via TMDb)
- 📄 **Movie Details** (overview, ratings, release date, poster, etc.)
- 🎟️ **Ticket Booking Flow**
- 🗂️ **User Data Storage** (Firebase Realtime Database)
- 🖼️ **Image Upload / Storage** (Firebase Storage)
- 🧩 **MVVM Architecture** for clean separation of concerns

---

## 🧱 Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM
- **Networking:** Retrofit
- **Backend / Auth / Storage:** Firebase (Auth, Realtime DB, Storage)
- **Android:** Jetpack components (ViewModel, LiveData, etc.)
- **API:** TMDb API :contentReference[oaicite:2]{index=2}

---

## 📸 Screenshots / Demo

> Add screenshots inside a `/screenshots` folder and update the links below.

| Screen | Preview |
|-------|---------|
| Login | `screenshots/login.png` |
| Home | `screenshots/home.png` |
| Movie Details | `screenshots/details.png` |
| Booking | `screenshots/booking.png` |

📽️ Demo Video: `WhatsApp.Video.2025-09-28.at.17.06.28.mp4` :contentReference[oaicite:3]{index=3}

---

## 🛠️ Setup & Run Locally

### 1) Clone the repository
```bash
git clone https://github.com/Priyadharshini0101/TicketBookingApp.git
cd TicketBookingApp
