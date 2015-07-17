Sample Spring Application
==================================================

This is not a real world project. This project can be used to start a new project with ready-to-run configurations.

The main thing here is to keep the latest versions of the softwares and the tools together. 

## This project contains the following configurations and libraries:

- Spring Framework (version: 4.1.5.RELEASE)
- Java Standard Tag Library (JSTL, version: 1.2)
- A Customized Logger built on top of SLF4J (version: 1.5.8)
- Spring Security 3.2.6.RELEASE
- [SB Admin 2](https://github.com/IronSummitMedia/startbootstrap-sb-admin-2) : Open source admin panel template for Bootstrap
- Hibernate 4.0.1.Final
- MySQL 5 support

## This project will contain the following technologies in the future:

- Angular JS
- MySQL
- A Sample RESTful API
- Tiles View Resolver
- Hazelcast

## Setup and Deploying to Jetty for Development Environment

### Linux Environment
Assuming that you have already downloaded a jetty distribution for java7 in a linux environment.

1. Create a database named `j2eestarter` in MySQL:

    ```
    mysql > CREATE DATABASE j2eestarter DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
    ```

2. Build the project:

    ```
    $ mvn clean install
    ```

3. Create a jetty base:

    ```
    mkdir ~/dev_base
    cd ~/dev_base
    java -jar $JETTY_HOME/start.jar --add-to-startd=http,deploy,webapp,jsp
    ```

4. Copy the war file into jetty, assuming `$PROJECT_HOME` is the folder for this project:

    ```
    cp $PROJECT_HOME/target/J2EEStarterPack.war ~/dev_base/webapps/ROOT.war
    ```

5. Run the project for the first time:

    ```
    cd ~/dev_base
    java -jar $JETTY_HOME/start.jar
    ```

  1. If it throws `Address already in use` exception, change the `jetty.port` port in `~/dev_base/start.d/http.ini` file.

6. Shutdown the jetty server by pressing `CTRL + C`

7. Since we use `hibernate.hbm2ddl.auto=update` property, Hibernate created our database schema automatically in first run. Now, run the SQL script in `$PROJECT_HOME/files/setupScript/import.sql`. This will create initial ACL rules.

8. Run the project again. It is ready to use:

    ```
    cd ~/dev_base
    java -jar $JETTY_HOME/start.jar
    ```

9. Navigate to `http://localhost:8080` in your browser. If you changed the port in step 5.1, use the new port. It will show the home page. Initial username/password combination for admin user is `admin/123456`. After login, you can navigate to `http://localhost:8080/admin` to see the admin panel.

10. That's all. Enjoy it and please contribute. All pull requests to this project are welcome.
    
## Running in Eclipse using Maven Tomcat Plugin

1. Import the project into Eclipse as `Existing Maven Project`

2. Create a database named `j2eestarter` in MySQL:

    ```
    mysql > CREATE DATABASE j2eestarter DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
    ```

3. Right click to the project, `Run as -> Maven build...`

4. Write `tomcat:run` to Goals.

5. Choose `Skip tests` checkbox.

6. Add a parameter named `port` and whose value is the port you want to use, for example, 1991.

7. Click Run.

8. Since we use `hibernate.hbm2ddl.auto=update` property, Hibernate created our database schema automatically in first run. Now, run the SQL script in `$PROJECT_HOME/files/setupScript/import.sql`. This will create initial ACL rules. In Eclipse console, click red square button to terminate the tomcat process.

9. From Run Configurations window, Run the same maven configuration again. The project is now started. Navigate to `http://localhost:1991` in your browser. If you used a different port in step 6, use that port. It will show the home page. Initial username/password combination for admin user is `admin/123456`. After login, you can navigate to `http://localhost:1991/admin` to see the admin panel.

10. That's all. Enjoy it and please contribute. All pull requests to this project are welcome.