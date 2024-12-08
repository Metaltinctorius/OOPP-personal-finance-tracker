# Description
This application aims to simulate an economy market where the player can practice 
investments by responding to real-time events, trying to beat the index. The value 
lies in the educational aspect of economical trends and a way to practice without 
real currency.

## How to run

### Prerequisites
- Java JDK-21, either Oracle or OpenJDK should work.
- Maven (if chosing alternative 1).
- IDE with native maven support (if chosing alternative 2)

### Alternative 1 - From terminal
**Windows:**

Setup the current shell session temporarily to jdk21.  
From terminal use set and add your correct install location.
1. set JAVA_HOME=C:\Program Files\YourPathTo\JDK-21

2. git clone the project and locate to it

3. Do 'mvn clean javafx:run' from the project root.

**MacOS:**  

Setup the current shell session temporarily to jdk21.   
From terminal use export and add your correct install location.
1. export JAVA_HOME=$(/usr/libexec/java_home -v 21)

2. git clone the project and locate to it

3. Do 'mvn clean javafx:run' from the project root.

**Linux**

Setup the current shell session temporarily to jdk21.  
From terminal use export and add your correct install location.
1. export JAVA_HOME=/usr/lib/jvm/java-21-openjdk

2. git clone the project and locate to it

3. Do 'mvn clean javafx:run' from the project root.

### Alternative 2 - Using IDE
In intelliJ or other IDE's with native maven support,   
setup the project to use jdk21 and then run maven goal:  
'mvn clean javafx:run' from the maven execution shell.


