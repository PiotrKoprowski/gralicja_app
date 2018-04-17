# gralicja_app
An app for people visiting board games fairs

The purpose of the application is to help participants during board games fairs. The application allows registration / logging and arranging a common game during the event. The basic functionality is to choose a specific title from among the game database and create a "table" to which other users can join. The "table" stores information about the game, time, current and maximum number of users.
Currently, logging is done using spring security but requires adding validation of registration data.
The application is written as web. Ultimately, the application will be rewritten to the REST architecture to enable the creation of the version for Android and IOS, which will also force the rewriting of the front-end of the application. For this reason, currently this part of the application is written only to visualize the effect of work and check the functionality.
Plan for further development of the application:
1. Improving the functionality of game tables,
2. Allowing you to set up a table from the level of the game browser,
3. Adding a chat / comments to the table,
4. Confirmation of the email address,
5. Adding an interactive event map.