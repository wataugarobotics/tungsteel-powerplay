# Contributing

Instructions for setting up the repository/toolchain:
If you haven't already, create a Github account. Go to
[github.com/wataugarobotics/tungsteel-powerplay](https://github.com/wataugarobotics/tungsteel-powerplay) and click the "fork" button in the
top-right corner. This will create a copy of the repository in your account. Next, follow
[these steps](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)
for creating a personal access token and save it somewhere temporarily.
Next, install Android Studio. When it finishes downloading everything, there should be a
button labeled "Get from VCS". Click it and enter this URL, replacing with your Github
username and personal access token (replace the angle brackets too).
https://<USERNAME>:<ACCESS_TOKEN>@github.com/<USERNAME>/tungsteel-powerplay.git
Change the directory to wherever you want the project to live and click "Clone". If Android
Studio asks if it should do a Gradle sync, say yes. Once you've done all this and Android
Studio has finished syncing and installing, try clicking the green hammer icon at the top
center of the screen to build the project. If you get an error that says "SDK location not
found", go to  File > Settings > Appearance and Behavior > System Settings > Android SDK
and copy the Android SDK location. Open the file explorer sidebar and change the mode at the
top to "Project files". Create a new file in the project root called "local.properties" and
type `sdk.dir = <SDK_LOCATION>` into it. Try building again now. If it doesn't work or you
have other questions, ask on Discord. If you finish all of this, there are many online
tutorials about using Git, our version control system. A basic understanding of Git is
necessary to work on the project. To contribute your code to the main repository, commit and
push it in Android Studio. Click the "Contribute" button on your fork to open a pull request.
I or Kyle will look at it and either merge it or discuss it before merging.
