# The Namegame: Android

Leading scientists have proven, via science, that learning your coworkers names while starting a new job is useful. Your test project is to make it happen! The api is located at [http://www.willowtreeapps.com/api/v1.0/profiles](http://www.willowtreeapps.com/api/v1.0/profiles).


## Your mission

Present the user with six faces and ask them to identify the listed name. To spruce things up, implement a few features of your choice.

1. Stat tracking. How many correct / incorrect attempts did the user make? How long does it take on average for a person to identify the subject?
2. Spruce up transitions and image loading.  Don't let images pop in and show the user that loading is happening
3. Game modes:
    * Mat(t) Mode. Roughly 90% of our co-workers are named Mat(t), so add a challenge mode where you only present the users with A Mat(t).
    * Reverse mode: Show one face with 5 names. Ask the user to identify the correct name.
4. Hint mode. As people wait, faces disappear until only the correct one is left.
5. Insert your own idea here!


## Notes:
1. This exercise should take no more than 8 hours, please do not spend more than that!
2. The app needs to support portrait and landscape without using the manifest flag android:configChanges. Note that we're curious how you might go about solving for destroyed activities and fragments. 
3. This skeleton project worked off of an old version of the API. So you'll have to update the models appropriately

When reviewing your project, we will be focusing on the following areas:
* Code architecture
* Code quality
* Code correctness
* Overall creativity
Note: Feel free to include a text file describing your thoughts and approach if you feel that's appropriate. 

Good luck and have fun!
