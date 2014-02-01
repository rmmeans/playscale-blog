#!/bin/bash

if [[ $# != 1 ]]; then 
  echo "Usage: $0 {start|stop|restart}"
  echo "Example: $0 start"
  exit 1; 
fi 

PID_FILE="/home/vagrant/app.pid"

if [[ $1 == "start" ]]; then 
	if test -f ${PID_FILE}; then
    if ps -p `cat $PID_FILE` > /dev/null
    then
      echo "This application is already running. Please stop it at first."
      exit 1
    else
      rm -f ${PID_FILE}
    fi 
  fi

  sudo nohup /home/vagrant/FrontEndServer-1.0-SNAPSHOT/bin/frontendserver -Dserver.color=blue -Dhttp.port=9010 -Dpidfile.path=$PID_FILE -J-Xmx256M -J-Xms64M -J-Xss1m -J-XX:MaxPermSize=128M -J-XX:+CMSClassUnloadingEnabled -J-XX:+UseConcMarkSweepGC > nohup.out &
else
  if test -f ${PID_FILE}; then
    sudo kill `cat $PID_FILE`
    rm -f ${PID_FILE}
    echo -n "Stopping Play Application ..."
    RETVAL=$?
    if [ $RETVAL -eq 0 ]; then
      echo " - Success"
    else
      echo " - Failure"
    fi
  else
    echo "Play Application is not running"
  fi
fi