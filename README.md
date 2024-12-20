
# Crescendo

Crescendo is a music player Android application. Written in Kotlin, Crescendo works with your phone's local storage to read the music you have in your device.

As I like to see it, is an improved version of another same project I had named Sinfonia.
## Crescendo vs. Sinfonia

This Android application follows the same idea of another project I had in the past, Sinfonia. Sinfonia is a music player that I also wrote in Kotlin. The main difference with Crescendo is how the UI is written and how efficient is with memory.

Here is a list of the key differences between these two applications:

#### Sinfonia:
- UI is written in XML.
- For every user song, allocates a MusicPlayer object in memory. So if the user has 50 songs, Sinfonia creates 50 MusicPlayer objects.
- It doesn't use threads.
- If your device has low RAM memory, the application may crash or restart due to inneficient memory manegement.
- It only read files contained in the same application folder; Sinfonia can't access common user folders like Downloads or Music.

#### Crescendo:
- UI is written in Jetpack Compose, so it has the advantage of being reactive.
- For every user song, allocates only one MusicPlayer object in memory to play them all.
- It uses threads, so the UI thread isn't blocked, making it a more pleasant experience for users.
- Due to only having one MusicPlayer in memory, Crescendo doesn't crash or restarts itself like Sinfonia.
- It can read user songs from all folders.
## Set up

In order to try Crescendo, you have two ways of doing so. First:

1. Execute: git clone https://github.com/ulises-justo-saucedo/Crescendo.git
2. Open the project with Android Studio IDE.

After this, you can:

3. Set up an Android emulator.
4. Execute the application in the Android emulator.

Or:

3. Create an APK.
4. Install the APK in your Android device or in an Android emulator.

