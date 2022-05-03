# CSC 450 - Team Project
## Team Members
Nick Morris

Temo Meza

Llacki Bautista-Perez

Skylar Stockton

Ixchel Peralta

## User Roles
### User
We anticipate our app will function the same for each user with all features being available. This user can range from anyone who is interested in security to a cybersecurity analyst.

## Building the Project
In android studio, from file, make a new project from version control. If not signed in to git, do so now through the interface. The easiest way would be to generate a token (you would sign in to git in a web browser, and android studio would automatically choose the right permissions to grant it). Once signed in, you should see the project. Click it and then click clone. What was cloned was the entire git project, so to work on the android studio specific project, you must open it. To do so, head back to file, open, and open the "SecurityApp" folder. It should have the android logo next to it. Once everything finishes loading/downloading, at the top right, there should be an elephant looking icon, "sync project with gradle files", click that, and give your project several minutes to build. Progress can be viewed at the bottom right of the screen. One final step, go to your google firebase,the project should have been shared, and you need to go to the project console, click the gear/settings, click "Project Settings", then scrool down until you see a donwload for google-services.json. Download it, and place it into the app folder of the SecurityApp folder. You should be good to go after placing the file in the right place.

*PLEASE REMEMBER GIT WORKFLOW*
* Pull/update project
* After completing something, regardless of size, commit
* After you felt you've done a lot, or have finished, pull/update once more, resolve conflicts, and finally commit and push your work

## Running Tests
* In android studio, pull down on the menu where the build to be run is selected.
* Name this run configuration "All Tests"
* From here, ensure the module is set to Security_App.app
* Set test to "All in Module".
* Click OK.
* Now that the run configurations have been set up, simply select the "All Tests" configuration and click run.
