rm -f WEB-INF/classes/*/*.class
javac -cp ".:../../lib/servlet-api.jar" WEB-INF/classes/*/*.java

# also restart server (path assuming we are in ROOT folder in webapps)
bash "../../bin/shutdown.sh"
bash "../../bin/startup.sh"