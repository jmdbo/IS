Here are all the binaries you will need from the restlet GAE edition:
Put them all in war/WEB-INF/lib (build path is already set to search for them in this repository)

From restlet-gae-versionNumber/lib/com.fasterxml.jackson_versionNumber

com.fasterxml.jackson.annotations.jar
com.fasterxml.jackson.core.jar
com.fasterxml.jackson.csv.jar
com.fasterxml.jackson.databind.jar
com.fasterxml.jackson.jaxb.jar
com.fasterxml.jackson.smile.jar
com.fasterxml.jackson.xml.jar
com.fasterxml.jackson.yaml.jar

From restlet-gae-versionNumber/lib
org.restlet.ext.gae.jar
org.restlet.ext.gwt.jar
org.restlet.ext.jackson.jar
org.restlet.ext.servlet.jar
org.restlet.jar

From restlet-gae-versionNumber/lib/com.google.gwt.server_versionNumber
com.google.gwt.server.jar  

On Eclipse: 
If you don't have GAE plugin for eclipse installed, go to Help -> Install new software -> Add 
and add the GAE repository at http://dl.google.com/eclipse/plugin/4.3

If you don't have Android SDK either Eclipse will ask you to install it, You will need a version of android >= 4.0.3 (API 15+ required)
Then go to Window -> Android SDK Manager and install/update the latest version of android build tools
