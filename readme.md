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

##### GET /GetVolunteerMatches/{volunteer_id}


Responds with JSON containing all matched opportunities for volunteers based on their interest ,skills and availability.
```json


    "Firstname":"Taslima",
    "lastname":Patel",
    "contactnumber":01234567,
    "username":"Tassu26",
    "employername":"Tech Returners",
    "primarylocation":"Preston",
    "numberofdays":3,
    "startdate":26-05-2021,
    "enddate":30-05-2021,
    "Web_Design":true,
    "SEO":true,
    "Graphic_Design":false,
    "Teaching":True,
    "Public_Health":False,
    "Empowerment":True,
    "Sports":False,
    "Construction":True,
    "Cooking":False,
    "Accessibility":True,
    "Mental_Health":False,
    "Event_Planning":True,
    "Gardening":False,
    "Music":True,
    "Dance":True,
```

---

##### GET /GoodcauseMatches


Responds with JSON containing all matched opportunities for good cause based on their skills,interest and availability.

---


---

##### POST /SaveVolunteerHandler

Will create a new volunteer information:

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

##### PUT /SaveVolunteerIntrestsHandler/:volunteerId


Will add more information about volunteers desired intrests for volunteering  in to a volunteer table :

```json   
   
   {
        "web_design" : "web_design",
        "SEO" :"SEO",
        "Graphic_Design" :"graphic_Design",
        "Teaching" : "teaching",
        "Public_Health" : "public_Health",
        "Empowerment" : "empowerment",
        "Sports" : "sports",
        "Construction" : "construction",
        "Cooking" : "cooking",
        "Accessibility" :"accessibility",
        "Mental_Health" : "mental_Health",
        "Event_Planning" : "event_Planning",
        "Gardening" :"gardening",
        "Music" : "music",
        "Dance" : "dance",
}
```
##### POST /sgoodcauseregistration

Will create a new good cause  information :

```json

   {
   "descriptionofgoodcause":"This is a charity with deals with children",
   "firstname":"Jane",
   "lastname":"Doe",
   "emailaddress":action4children@gmail.com",
   "contactnumber":234989000,
    }
```
##### PUT /SaveGoodCauseDetailsHandler

Will add more infornation to an existing  good cause  information : 

```json
{
  "good_cause_name":"Action 4 Children",
  "descriptionofgoodcause":"more information about action 4 children"
}
```
##### POST /SaveGoodCauseOpportunity/{good_cause_id}

Will add more infornation to an existing  good cause  information : 

```json
{
    "opportunityname":"Action 4 Children",
    "opportunitydescription":"More Action 4 children",
    "Date" :24-04-2021,
    "web_design":true,
    "SEO":true,
    "Graphic_Design":false,
    "Teaching":true,
    "Public_Health":true,
    "Empowerment":false,
    "Sports":true,
    "Construction":false, 
    "Cooking":true,
    "Accessibility":false,
    "Mental_Health":true,
    "Event_Planning":false,
    "Gardening":true,
    "Music":false,
    "Dance":true,
    "Location":false,
}
```

Will add more infornation to an existing  good cause  information : 

```json
{
  "good_cause_name":"Action 4 Children",
  "descriptionofgoodcause":"more information about action 4 children"
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
