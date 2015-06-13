@echo off
echo valid commands:
echo   mvn clean                        :: cleans all target directories.
echo   mvn generate-sources             :: generates the component descriptiors.
echo   mvn compile                      :: generates the component descriptiors and compiles.
echo   mvn test                         :: compile and unit test
echo   mvn surefire-report:report       :: Run run test´s ang generate surefire test report at target/site/surefire-report.html  
echo   mvn install                      :: test and copies the generated jars to the local repo.
echo   mvn package                      :: generates all jars and wars.
echo   mvn site                         :: generates a project website with reports.
echo   mvn test -fn                     :: compile and unit test and ignore errors
echo   mvn -Dmaven.test.skip=true clean install 
echo                                    :: installs without doing unit tests.
echo   mvn -Dmaven.test.failure.ignore=true 
echo                                    :: ignores build failures
echo   mvn tomcat:deploy                :: deployment in server with name tomcat (settings.xml)
echo   mvn tomcat:redeploy              :: deployment in server with name tomcat (settings.xml)
echo using defined profiles (dev, test, prod [dev is standard profile]):
echo   e.g.: mvn -Pdev package
echo                                    :: builds the application with "dev" configuration
echo   mvn package assembly:assembly    :: builds all packed sioux tools
echo ----------------------------------------------------------------

set JAVA_HOME=W:\java\jdk1.6.0_18
set MAVEN_HOME=W:\java\apache-maven-3.0.3
rem set SVN_HOME=S:\Software\subversion
rem set PERL_HOME=S:\software\perl\perl

set path=%MAVEN_HOME%\bin;%JAVA_HOME%\bin;%PATH%;%SVN_HOME%\bin
set path=%path%;%PERL_HOME%\bin
set maven_opts=-Xmx1024M -Dcom.sun.management.jmxremote
cd ..\code
cmd /k
