call mvn clean
call mvn package
copy src\other\veloxi.bat target
call mvn assembly:assembly
