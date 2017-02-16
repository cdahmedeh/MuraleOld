set PATH=%PATH%;%~dp0\jre\bin
set JAVA_HOME=%~dp0\jre

set CLASSPATH=%~dp0\murale.jar;%~dp0\muralelib.jar;%~dp0\lib\*;

jre\bin\javaw.exe net.cdahmedeh.murale.app.AppStarter -classpath "%CLASSPATH%"