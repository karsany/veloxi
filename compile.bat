call mvn clean
call mvn deploy
cd target
ren veloxi*.jar veloxi.jar
cd ..
call mvn assembly:assembly