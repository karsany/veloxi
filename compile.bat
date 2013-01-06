call mvn clean
call mvn deploy
cd target
ren veloxi*.jar veloxi.jar
cd ..
copy src\other\veloxi.bat target
call mvn assembly:assembly

