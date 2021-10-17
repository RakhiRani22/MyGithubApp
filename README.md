# MyGithubApp
 The application connects to the  Github apis and display the commit informations
 
 The application will launch the UserInputActivity to take user input.
 UserInputActivity will display 2 edit text for user input of username and repository name and 2 buttons for displaying the Commit information in the list.
 When user clicks on button, application will validate the username and repo name. If the user input is valid, application will fetech the commit informations.
 When user clicks on button "Display Default Commits", application will take defalt username = "RakhiRani22" and repository name = "MyGithubApp".
 When ser clicks on button "Display Commits", application will take the run time user input (username and repository name) from the edit text.
 Validation for user input is performed and the error message in form of Toast will be displayed at run time in case of any error.
 Once the username and repository name is valid, the second activity will display the commit information (Author name, Commit hash and commit message) in a list     (Recycler View).
 In case the commit or repository is not found, error message will be displayed in form of Toast message at run time.
 The data is fetched at runtime using the GitHub apis. RetroFit library is used for network calls and Gson library is used for Json data parsing.
 Dagger is used for instance injection.
 Unit test cases are added to perform the functional testing.
