# PopularMoviesStage1
**Summary**
This is an Android app developed as part of the Udacity's Android Development Nanodegree Program.

**App description**
This app has two views:
- On the main view, you can see a gid with movie posters. You can sort them by popularity or by ratings
- When you tap on any of the posters, you will be taken to a movie detail view, where you can see the original title of the movie, its poster, release date, average rating and sinopsis

**How was this done**
- A RecyclerView with a grid layout was used to create the main activity screen.
- AsyncTask was used to retrieve the movies' information from the Movie Database API on a background thread

**Technologies used**
Java

**Please note**
In order to run the app successfully, the Movie Database (TMDb) API key needs to be added to the API_KEY constant in the NetworkUtils class.
