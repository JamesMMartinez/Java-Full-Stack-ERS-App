# Java-Full-Stack-ERS-App

## Project Description
Expense Reimbursement System created using Java, JavaScript, HTML, CSS, JDBC, AWS RDS, and PostgreSQL. Inspired by the famous Japanese Manga Series One Piece this project uses servlets to navigate between views and allow employees and managers to perform various tasks.

## Technologies Used

Java - version 1.8
PostgreSQL - version 42.2.5
Apache Maven - version 3.6.3
Apache Tomcat - version 9.0.45
Log4j - 1.2.17
Junit - 5.4.2

## Features

List of features ready and TODOs for future development
* Users can login and be redirected to a employee or manager homepage based on credentials.
* Employees can submit a reimbursement request based on food, travel, lodging, and other to be reviewed by a manager.
* Employees can view their pending and resolved request, as well as the manager who resolved them.
* Employees can view their account information and edit their user credentials
* Managers can view employee reimbursement requests by employee ID or by request status(pending/resolved)
* Managers can approve or decline employee reimburement requests
* Managers can view all employees and their information aside from their password 

To-do list:
* Implement a feature allowing a user to upload a reciept for review
* Provide additional CSS formatting for improved look

## Getting Started
   
git clone 

Upon cloning the repository the application should be ready to run on your local host, as long as the correct versions of the technologies listed in "technologies used"  
are the same. Once the Apache Tomcat server is started everything will be ready to go.

The starting view for the application should be the login page and with the link similar to http://localhost:8081/ERSApp/static/login.html dependent on the local host port

## Usage

To get started the following login information can be used to access the apps features:

**Employee:**  *Username:* thepirateking1 *Password:* Rubberman1

**Manager:** *Username:* namib123       *Password:* Millionb3rri3s

After successful login the top navigation bar is used to navigate between views and features.

## License

This project uses the following license: GNU GENERAL PUBLIC LICENSE (<LICENSE>).
