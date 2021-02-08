# The Name Game for Android

Leading scientists have proven, via science, that learning your coworkers names while starting a new job is useful. Your test project is to make it happen!


## Your mission

1. Use this [JSON API](https://namegame.willowtreeapps.com/api/v1.0/profiles), which returns a list of employees along with their headshots, as a datasource for the game. A swagger spec for this API will be created soon to use as a reference.

2. Use [these Figma designs](https://www.figma.com/file/yUzRfmltt1m1UT9UkKL3y6/namegame?node-id=177%3A0) as a reference for your implementation of the game. Try to match these designs as closely as possible. All assets are either system defaults or can be exported from Figma directly.

3. Include an app icon, main menu, and game screen as shown in the designs. Don't forget to support both landscape and portrait mode.

![Landscape Main Menu](assets/screenshots/landscape_home.png)

4. Clicking "Practice Mode" or "Timed Mode" on the menu screen will navigate to the game screen. On the game screen, the game will select 6 employees at random from the list of employees and display their headshots in random order. Out of the 6, the name of one of those random employees will appear on the screen.

5. The user must select the headshot that belongs to that person. If they guess correct the application will show that and a new 6 random employees will be displayed along with another name.

6. In practice mode this will continue until a user guesses incorrectly in which case the screen will say "Game Over!" and display their score. Clicking "OK" will navigate them back to the Menu.

7. In timed mode the user will be able to make incorrect guesses until they find the correct user, in which case a new set of random employees will be displayed. This mode ends when a timer runs out, feel free to make the timer as long or short as you would like. Once the timer runs out, their score is displayed and clicking "OK" navigates them back to the Menu.

8. You can implement this app however you choose, that said, your Name Game test project will be evaluated according to [this rubric](namegame_evaluation_rubric.pdf).