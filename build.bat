del /Q /F /S "WEB-INF\classes\controllers\*.class"
del /Q /F /S "WEB-INF\classes\models\*.class"
javac -cp .;.\WEB-INF\classes\;..\..\lib\servlet-api.jar "WEB-INF\classes\models\*.java"
javac -cp .;.\WEB-INF\classes\;..\..\lib\servlet-api.jar "WEB-INF\classes\controllers\*.java"
call "C:\Users\hp\Desktop\apache-tomcat-9.0.59\bin\shutdown.bat"
call "C:\Users\hp\Desktop\apache-tomcat-9.0.59\bin\startup.bat"