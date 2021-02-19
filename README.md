# Text Formatter Service

## Contents

- [Introduction](#Introduction)
- [Installation](#Installation)
- [Usage](#Usage)

## Introduction

This is a REST API service in response to the below problem statement:
https://recruit.hr-on.com/fpassthru.php?file=6051682&chk=1295c3c4e28530f3350acf3ac7f74c2a

# Installation

This service requires maven and java 11 to be installed on your system. Also, HTTP port 8080 must be available on the
host.

compiled and run using maven

```bash
$ cd format-text
$ mvn spring-boot:run
```

This app is docker compatible. can run application on docker container with below commands docker engine must be running

```bash
$ cd format-text
$ docker-compose up
```

# Usage

Use the following command to return a left-aligned String.

```bash
curl --location --request POST 'http://localhost:8080/align/RIGHT/length/10' \
--header 'Content-Type: text/plain' \
--data-raw 'This text should be left aligned '
```

response:

```bash
This text 
should be 
left      
aligned   
```

similar way right-aligned and centre-aligned as below

Right aligned:

```bash
curl --location --request POST 'http://localhost:8080/align/RIGHT/length/10' \
--header 'Content-Type: text/plain' \
--data-raw 'This text should be left aligned '
```

response:

```bash
 This text
 should be
      left
   aligned
```

Center aligned:

```bash
curl --location --request POST 'http://localhost:8080/align/CENTER/length/10' \
--header 'Content-Type: text/plain' \
--data-raw 'This text should be left aligned '
```

response:

```bash
This text 
should be 
   left   
 aligned  
```
 
