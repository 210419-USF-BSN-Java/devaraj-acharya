# Employee Reimbursement System - Devaraj Acharya #
## Overview ##

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Getting Started ##

### Clone the project to the local environment ###
##### git clone https://github.com/210419-USF-BSN-Java/devaraj-acharya #####

#### After cloning the project unzip the folder, set up database and import ers-project-1 to integrated development environment Eclipse/Intellij/Visual Studio ####
#### To set up DataBase ####
- copy the postgresql script from devaraj-acharya/Project-1 ERS DB/ERSDB_script.sql and create schema and tables in postgresql DB
#### To set up project in Eclipse ####
- File => Open Projects from File System => Directory 
- choose the location for the project ers-project-1
- Select Folder and Finish
- Go to com.ersproject.utility
- open the ConnectionUtilityAWS.java and change the RDS_HOSTNAME to the url of your DB and RDS_PASSWORD to the password of your DB
#### After importing the project and changing DB credentials make sure Apache Tomcat is installed and running in the Eclipse and change the prot no to 8085 ####
- Right click on project => Run As => Run on Server
- In your browser paste the url http://localhost:8085/ers-project-1/login
- you will see login page, use the dummy user login credentials from  ERSDB_script.sql to login

#### After using user login credential  ####
- An user can login
- An user can view the user Homepage
- An user can logout
- An user can submit a reimbursement request
- An user can view their pending reimbursement requests
- An user can view their resolved reimbursement requests
- An user can view their information
- An user can update their information

#### For Employee/Manager activities use the manager/employee login credentials  ####
- A Manager can login
- A Manager can view the Manager Homepage
- A Manager can logout
- A Manager can approve/deny pending reimbursement requests
- A Manager can view all pending requests from all employees
- A Manager can view all resolved requests from all employees and see which manager resolved it
- A Manager can view all Employees
- A Manager can view reimbursement requests from a single Employee

### Technology Used ###
Java, Backend Programming Language </br>
HTML + CSS + JavaScript, Frontend Programming Languages </br>
Apache Maven, Build Automation Tool and Package Manager </br>
Apache Tomcat + Java Servlets, Server Environment </br>
Java Database Connectivity, Database Interactivity API </br>
PostgreSQL, Relational Database Management System </br>
AWS RDS, Cloud Based Distributed Relational Database Service </br>
JUnit, Mockito, Unit Testing Framework </br>
Log4j, Logging Utility </br>

### Notes and ToDo ###
There is an upload receipt functionality that hasn't been implemented, which was considered a stretch goal.

Input validations need to be implemented
