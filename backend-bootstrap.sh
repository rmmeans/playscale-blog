#!/bin/bash

cp /vagrant/BackEndDocServe/target/universal/BackEndDocServe-1.0-SNAPSHOT.zip /home/vagrant/
su - vagrant -c "unzip /home/vagrant/BackEndDocServe-1.0-SNAPSHOT.zip" 
nohup /home/vagrant/BackEndDocServe-1.0-SNAPSHOT/bin/backenddocserve -Dhttp.port=9012 -J-Xmx128M -J-Xms64M -J-Xss1m -J-XX:MaxPermSize=128M -J-XX:+CMSClassUnloadingEnabled -J-XX:+UseConcMarkSweepGC > nohup.out &