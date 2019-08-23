#!/bin/sh
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`

SERVER_NAME=sc-product
if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi
PIDS=`ps -ef | grep java | grep "$SERVER_NAME" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi
LOGS_DIR=""
if [ -n "$LOGS_FILE" ]; then
    LOGS_DIR=`dirname $LOGS_FILE`
else
    LOGS_DIR=$DEPLOY_DIR/Logs
fi
LOGS_DIR="/yang/Logs/sc-product"
if [ ! -d $LOGS_DIR ]; then
    mkdir -p $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

#LIB_DIR=$DEPLOY_DIR/lib
#LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`
#for file in ${LIB_DIR}/*.jar
#do CP=${CP}:$file;
#done
#for file in ${DEPLOY_DIR}/main_jar/*.jar
#do CP=${CP}:$file;
#done
#LIB_JARS=${CP}

CONFIG_PATH=${DEPLOY_DIR}/config
#CLASSPATH="${CONFIG_PATH}:${LIB_JARS}"


#JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_OPTS="-Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true"
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS="-Xmx1024m -Xms1024m -Xmn512m -XX:PermSize=256m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS="-Xms1024m -Xmx1024m -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo -e "Starting the $SERVER_NAME ...\c"

#cd classes

JAVA_NAME_OPTS=" -DSERVER_NAME=$SERVER_NAME"


nohup java $JAVA_OPTS $JAVA_NAME_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -jar $DEPLOY_DIR/app.jar > $STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1 
    COUNT=`ps -f | grep java | grep "$SERVER_NAME" | awk '{print $2}' | wc -l`
    if [ $COUNT -gt 0 ]; then
        break
    fi
done

echo "OK!"
PIDS=`ps -f | grep java | grep "$SERVER_NAME" | awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"
