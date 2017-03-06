# github-client

JIRA to GitHub Issue migration

This tool can be used to migrate your issues from JIRA to Github

Steps
First export issues from JIRA with the feilds you need.
Build this project using maven
Navigate to target directory
Copy the files from resources eg cp ../resources/* .
Replace the jira.csv with the file of your issues.
Configure the url, username and password in config.properties file
Execute ./github-client.sh 

