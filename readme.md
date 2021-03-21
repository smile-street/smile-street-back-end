# Smile Street Application - Backend

This is the back end API of a Tech for Smile Street Application, built throughout the [Tech Returners](https://techreturners.com) Your Journey Into Tech course. 
It is consumed by a front end React application, available [here](https://github.com/smile-street/smile-street-front-end) and connects to an RDS MySQL Database.

The hosted version of the application is available here:.

### Technology Used

This project uses the following technology:

- Serverless Framework
- JAVA
- SQL
- Mysql library
- AWS Lambda and API Gateway
- AWS RDS

---
---
### Endpoints

The API exposes the following endpoints:

---

##### GET /VolunteerMatches


Responds with JSON containing all matched opportunities for volunteers based on their interest ,skills and availability.

---

##### GET /GoodcauseMatches


Responds with JSON containing all matched opportunities for good cause based on their skills,interest and availability.

---


---

##### POST /Volunteer/SaveVolunteerHandler

Will create a new volunteer information  for an authorised  when sent a JSON payload in the format:

```json

{
        "firstname": "Taslima",
        "lastname": "Banglawala",
        "contactnumber": "123456",
        "username":"tassu26"
    }
```

##### PUT /UpdateVolunteerHandler/:volunteerId


Will add more information in to a volunteer table :

```json   
   
   {
        "employername": "Taslima",
        "numberofdays": 3,
        "startdate": 20-03-2021,
        "groupNum": 26-03-2021
}
```

---



### To build and deploy the API

#### Dependencies
1. A MySql database on [AWS](https://aws.amazon.com/) must be available.
2. [Serverless](https://www.serverless.com/framework/docs/getting-started/) must be installed and available with credentials set.

   eg To install via npm

        npm install -g serverless 

   To set credentials

        serverless config credentials --provider aws --key <key> --secret <secret>



#### To build and deploy

1. Clone the repository
2. To create the database tables and views run

        mysql -u <user> -p -h <database instance endpoint> < ./sql/createTables.sql

   To insert some test data run

        mysql -u <user> -p -h <database instance endpoint> < ./sql/insertData.sql

3. To set the environment

   Create a config.dev.json file of the form config.example.json e.g.

        {
        "DB_HOST": "some-rds-endpoint",
        "DB_NAME": "some-database-name",
        "DB_USER": "some-database-user",
        "DB_PASSWORD": "some-user-password"
        }

4. To build the application run

       

5. To deploy the lambda functions

        serverless deploy

---