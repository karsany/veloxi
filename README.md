# Veloxi - XML templating

## Description

Veloxi parses XML files and uses Velocity engine to use the parsed XML.

It is an alternative to [XmlGen], but without using Ant tasks.

## Usage

    java -jar veloxi.jar input.xml template.vm > outputfile.ext

## Compile

Prerequisites: A configured Apache Maven, and JDK 1.6.

You should run compile.bat file. This creates veloxi.jar in folder target.

   [XmlGen]: http://xmlgen.sourceforge.net/