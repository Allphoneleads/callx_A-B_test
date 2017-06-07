1. Once checked out from the git repository, import to IDE as File ----> Import ----> Gradle>>Gradle Project

2. Update the gradle.properties file with correct DB information in the local environment, the example values given in the file refer to the following set up.

Database name : apl_db
username : temp1
password : temp2

3. Once the properties file is updated right go to the project folder in command line and run the following command to set up the DB and create the tables using flyway

>> gradle flywayClean flywayMigrate

4. Once the DB is setup, run the following command to clean and build the jar file for the project 

>> gradle clean build

5. Execute the jar file with the following command to run the application 

>>java -jar build/libs/allPhoneLeads.jar

6. That would start the server. Instead of Step-4 and Step-5 together, we can execute the following command for the tasks of clean, build and deploy in one go. This is useful when we run the application in local.

>> gradle bootRun
