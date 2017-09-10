REST-RUSH
==========

General idea is to generate a basic entity-microservice project based on the parameters given in a JSON configuration file.

Generated project will use the following technologies:
* Java
* Spring boot (web module REST exposed endpoints for CRUD)
* Jackson

### Status
[![Build Status](https://travis-ci.org/hoofmen/rest_rush.svg?branch=master)](https://github.com/hoofmen/rest_rush)

### Relevant Development Tools
* Java 8
* Spring boot 1.5.6.RELEASE
* Jackson 2.8.5

### Configuration file (JSON)
You will require to first create the JSON configuration file in where you should specify details about the project you want to generate.

E.g.
```json
{
	"project-name":"My website accounts",
	"description":"Service that allows CRUD operations for my website accounts",
	"java":{
	  "version":8,
	  "base-package":"com.mywebsite.account"
	},
	"maven":{
	  "groupId":"com.mywebsite",
      "artifactId":"rest-rush"
	},
	"entity": {
		"id":"1d4f35e5-ded5-fd9b-ed15-867d35ce21bf",
		"name":"troll_1991",
		"latest-posts":{
		  ...
		}
	}
}
```
Important to notice the "entity" field, that should contain the main Entity JSON for which you're generating the service.

### How to use

```sbtshell
java -jar rest-rush.jar configuration.json
```
Passing the configuration file, a new project will be generated following feature-packaging standard.

