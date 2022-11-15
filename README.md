## Mobile Project

This project contains the automation UI tests for pipefy app (Android).

### Setup
The following guidelines explain how to setup your local environment to be able to develop and run the automated tests.
#### 1. Install basic tools

- [Android Studio](https://developer.android.com/studio)
- Create a device and set its name in `android.deviceName` variable in `src/main/resources/local.properties` file

#### 2. Install other dependencies
Open a terminal and install the following packages:

Node
````
$ brew install node
````

Appium
````
npm i -g appium
````

Appium-doctor
````
npm i -g appium-doctor
````

Doctor
````
$ brew doctor
````

#### 3. Java setup

````
$ brew install --cask adoptopenjdk/openjdk/adoptopenjdk11
````

#### 4. Setup environment variables
   Create a new file on the user’s root directory called .zprofile and set the environment variables.

````
$ cd
$ touch .zprofile
$ open .zprofile
````

Add the following content to the opened file.
````
#automation project
export PATH="/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/sbin"
export ANDROID_HOME=/Users/${path_name}/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/tools/bin
export PATH=$PATH/:$ANDROID_HOME/platform-tools
export JAVA_HOME=$(/usr/libexec/java_home)
export ANDROID_NDK=/Users/${path_name}/Library/Android/sdk/ndk-bundle
export AUTOMATION_SECRET=UHDFL+MKADO=.NSDKNK
export PATH="/usr/local/opt/node@10/bin:$PATH"
export APPIUM_HOME="/usr/local/lib/node_modules/appium/build/lib/main.js"
````

Save .zprofile file

````
$ source .zprofile
````

#### 5. Configure IntelliJ

The standard IDE used to run our automation framework is IntelliJ. Feel free to use any IDE you might prefer, but be aware that the configs are not going to be the same as described in this section.

If you do not have IntelliJ installed, you can install it directly or via Toolbox

[Direct Link](https://www.jetbrains.com/pt-br/idea/download/)

[Toolbox (recommended)](https://www.jetbrains.com/pt-br/toolbox-app/)

### Run tests:

- Edit configurations
- Add new configuratiob -> TestNG
- In test kind select -> Suite
- Define how you would like to run test
Example:
````
mobile-project/src/test/resources/config/singleTestsWithoutRerun.testng.xml
````

- In VM options set:
````
-ea
-Dplatform=android
-Denvironment=local
-Dcucumber.filter.tags="@vivi"
-DappPath=/Users/vivianereis/test/Downloads/pipefy-workflow-processes_2.2.1.apk
````

- Play it 