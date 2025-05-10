@echo off
set MAVEN_CMD=mvn clean test

echo Running tests on Chrome...
%MAVEN_CMD% -Dbrowser=chrome

echo Running tests on Firefox...
%MAVEN_CMD% -Dbrowser=firefox

echo Running tests on Edge...
%MAVEN_CMD% -Dbrowser=edge

echo All browser runs completed.
pause
