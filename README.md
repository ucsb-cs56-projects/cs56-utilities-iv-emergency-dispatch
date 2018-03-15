# isla-vista-emergency-dispatch
Interactive map showing emergencies dispatched by SB County Fire Department with real time updates.

## View on Heroku
https://iv-emergency-dispatch.herokuapp.com/

## Build Locally Using Maven
```
git clone https://github.com/ucsb-cs56-projects/cs56-utilities-iv-emergency-dispatch.git
cd cs56-utilities-iv-emergency-dispatch
mvn clean package
java -jar target/iv-emergency-dispatch-0.0.1-SNAPSHOT.jar
```
Then navigate to localhost:8080

## W18 Final Remarks
Congratulations for getting to work on IV Emergency Dispatch. IV Emergency Dispatch is web app that displays locations of real time emergencies in Isla Vista.

This project, while it may seem complicated, will expose you to a lot of really cool technologies. To make life easier for you, Manuel and I have provided documentation covering the technologies we used which can be viewed in the /docs directory.

Since this was a greenfield project for us, there are definitely a lot of issues to tackle. We tried our best to avoid any bugs so you should be able to jump into adding features as soon as posssible. The easiest way to get started, in our opinion, is to seed the database with an adequate number of emergency tweets for testing purposes. Currently, the twitter stream is functional but since this project will not be worked on for quite some time, the heroku dyno will sleep and new tweets will not be streamed and added to the database.

We wish you the best of luck on this project and really hope you enjoy working with the technologies we used. Good luck!

[credentialsLink]: credentials.md