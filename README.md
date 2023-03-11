# API Automation Rest Assured

---
This is DMONEY API Automation project. The project is implemented using RestAssured and TestNG.
The test cases are written using the standard test case format. The reporting is done using Allure

### Test Cases
* [dmoney api test case](https://docs.google.com/spreadsheets/d/1aLoDU6Rhy-oIePIUNRm0kQjcJCGx52VuMHjpx5Jswws/edit?usp=sharing)

### Languages And Frameworks
|                          |                  |
|--------------------------|------------------|
| Programming Language     | **Java**         |
| Java Library             | **Rest Assured** |
| Testing Framework        | **TestNG**       |
| Build Tool               | **Gradle**       |
| IDE                      | **IntelliJ**     |
| Test Reporting Framework | **Allure**       |



---
#### How to run the project
1. Clone this project
2. Open cmd in the root folder
3. Give the following command.

```
gradle clean test
 ```

#### How to generate allure report
1. Open cmd in the root folder
2.  Give the following commands

```
allure generate allure-results --clean -o allure-report
```
```
allure serve allure-results
```

### Generated Reports
![image](https://user-images.githubusercontent.com/41513761/224503737-739490ce-4e6e-4bec-92da-9dd3596cef76.png)
![image](https://user-images.githubusercontent.com/41513761/224503824-87e0d6d8-bf9f-45e5-8e6b-2da46b790d7f.png)
![image](https://user-images.githubusercontent.com/41513761/224503803-4b8f42e2-b39f-47a3-9454-bb1d80060e83.png)



#### Feedback
If you have any feedback, please reach out to me at md.mostafa.akash@gmail.com

