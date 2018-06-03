#!/bin/bash
mvn clean install 
docker build -t sp.jv.guild-post .
