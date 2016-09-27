# moda-search
This is the basic version of moda-search project. 

## Prerequisites
- Gradle 2.7+
- Java 8
- Apache tomcat 7+
- Spring MVC 4.3.3

## tomcat configuration
1. Open ~/tomcat/conf/context.xml
2. Add below line inside <Context> bean 
> <Environment name="ConfigSettings" value="<PREFERRED_LOCATION>/config.properties" type="java.lang.String" override="false"/>
Note : "ConfigSettings" is the constant. It should not be changed.

## Running Build
The default task is *war* which is provided by plugin. So running *gradle clean build* from command line will run *war* and it will create a war in build/libs folder. 

Once the war is created, copy the war file inside your tomcat webapps location.
> cp -rvf <Project_Location>/build/libs/moda-search.war ~/tomcat/webapps/

## Tomcat service start up and shutdown.

Below command to start your tomcat.
> ~/tomcat/bin/startup.sh 

Below command to stop your tomcat.
>~/tomcat/bin/shutdown.sh

Note : The above commands will be working if tomcat configured in your home path. If tomcat is configured in different location, kindly the change command accordingly.
