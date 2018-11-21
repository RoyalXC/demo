echo "Stopping demo"
pid=`ps -ef | grep demo-1.0.0.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
   echo "kill -9 的pid:" $pid
   kill -9 $pid
fi