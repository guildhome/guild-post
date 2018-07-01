#!/bin/bash
./mvnw clean package 
docker build -t sp.jv.guild-post .
