@echo off
rem SET JAVA_HOME=c:\Program Files\Java\jdk1.6.0_37\
rem SET VELOXI_HOME=d:\dev\!incubator\veloxi\veloxi\target\
rem %1 - input
rem %2 - template
rem %3 - output
SET VELOXI_JAVA_EXE="%JAVA_HOME%\bin\java.exe"
SET VELOXI_JAR="%VELOXI_HOME%\veloxi.jar"

%VELOXI_JAVA_EXE% -jar %VELOXI_JAR% %1 %2 > %3