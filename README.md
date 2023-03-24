                                                           Music App - Kotlin Android App with Beautiful UI
This is an Android app made by Kotlin that allows users to search for and listen to their favorite songs. The app fetches music data from Last.fm API and uses Room database to store the data locally. Coroutine scope has been implemented for efficient and faster data fetching.


Installation
To run the app, you will need to have the following:
Android Studio 4.0 or above
Last.fm API Key

Follow these steps to install the app:
Clone the repository by running the following command in your terminal:
bash
Copy code
git clone https://github.com/shantanu49001/MusicWkiProject.git
Open the cloned project in Android Studio.
Replace LASTFM_API_KEY with your own API key in Constants.kt.
kotlin
Copy code
const val LASTFM_API_KEY = "your_lastfm_api_key_here"
Build and run the app.


Features
Fetches music data from Last.fm API
Beautiful user interface
Uses Room database to store data locally
Coroutine scope for efficient data fetching

Usage
Upon opening the app, users will be greeted with a beautiful UI that allows them to search for songs. Users can search for songs by typing in the search bar and hitting the search button. The app will then fetch the music data from Last.fm API and display the results to the user.
Users can also listen to the songs by clicking on the song card. The app uses Room database to store the music data locally, which means that users can still access the music data even if they are not connected to the internet.

Limitation:
1.Couldn't add album images returned by api as the json data was dirty from api side
2.App doesn't support screen resolution and launguage support.

🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢❤️
Find the images to the ui alog with the clip demonstrating the ui:
![1](https://user-images.githubusercontent.com/70522639/227562209-650f90d5-b218-4e5b-83cd-e5e9081e4c04.jpg)
![2](https://user-images.githubusercontent.com/70522639/227562223-9ba1ccf5-c6ca-4e76-8d6e-2be4fa04c2d1.jpg)
![3](https://user-images.githubusercontent.com/70522639/227562226-80c7975c-6d1b-43b9-8f32-3bafa15aeea7.jpg)
![4](https://user-images.githubusercontent.com/70522639/227562229-e3119f1b-1c3a-4347-a6cd-47b8214aee44.jpg)

https://user-images.githubusercontent.com/70522639/227564166-5023edf3-63da-48c5-aeef-8540fbbe8036.mp4
🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢❤️

