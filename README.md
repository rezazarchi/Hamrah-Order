# Hamrah Order

A simple Android application for managing order addresses, built with Kotlin and modern Android development best practices. This project is actually as part of a job interview task.

## Features
- **Orders Addresses List**: Displays a list of saved addresses with a floating action button (FAB) to add a new address.
- **Add New Address Flow**:
  1. **Register Details**: User writes address and other details.
  2. **Select Location on Map**: User selects the location using Maps.
  3. **Submission Progress**: A progress screen while submitting the address to the server.
- **Error Handling**: All errors are handled and displayed using a Snackbar.

## Technologies Used
The project follows **Clean Architecture** principles and is built using:
- **Jetpack Compose** for UI development
- **Coroutines & Flows** for asynchronous programming
- **ViewModel** for state management
- **Material 3** for modern UI design
- **Navigation Components** for navigation between screens
- **Coil** for image loading
- **Koin** for dependency injection
- **Retrofit** for networking
- **Kotlin Serialization** for JSON parsing
- **Immutable Collections** for stable state handling
- **Maps.ir SDK** for location selection
- **JUnit & MockK & AssertK** for unit testing

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/rezazarchi/Hamrah-Order.git
   ```
2. Open the project in **Android Studio**.
3. You should have the latest version of Android Studio (Ladybug Feature Drop | 2024.2.2 Patch 1)
4. Build and run the project on an emulator or a real device (With Android 7.0 or above).

## Contributing
As this project was developed for a specific purpose, contributions are currently not being accepted. However, feedback and suggestions are welcome. Please open an issue for any discussions.

