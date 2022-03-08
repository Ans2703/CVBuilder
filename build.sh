rm -f WEB-INF/classes/*/*.class
javac -cp ".:../../lib/servlet-api.jar" WEB-INF/classes/*/*.java

# also restart server
bash "../../bin/shutdown.sh"
bash "../../bin/startup.sh"