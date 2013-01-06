# Veloxi - XML templating

## Description

Veloxi parses XML files and uses Velocity engine to use the parsed XML.

It is an alternative to [XmlGen], but without using Ant tasks.

## Usage

### Method 1

    java -jar veloxi.jar input.xml template.vm > outputfile.ext
	
### Method 2

You should set the JAVA_HOME and VELOXI_HOME environment variables,
and add Veloxi binary directory to the path. Then you can run:

    veloxi input.xml template.vm outputfile.ext
	
Other option is to set the JAVA_HOME and VELOXI_HOME in the veloxi.bat
file in the Veloxi binary directory, then just add the Veloxi dir to
the path.

## Compile

Prerequisites: A configured Apache Maven, and JDK 1.6.

You should run compile.bat file. This creates veloxi.jar in folder target.

   [XmlGen]: http://xmlgen.sourceforge.net/