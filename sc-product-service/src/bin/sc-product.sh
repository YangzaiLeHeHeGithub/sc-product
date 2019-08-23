#!/bin/sh
source /etc/profile
prog="sc-product"
exec_dir="/yang/apps/sc-product/bin"

start() {
if [ $UID -ne 0 ] ; then
echo "User has sudo privilege."
exit 4
fi
[ -d $exec_dir ] || exit 5
echo -n $"Starting $prog: "
[ -f $exec_dir/start.sh ] || exit 2
[ -x $exec_dir/start.sh ] || exit 2
$exec_dir/start.sh
retval=$?
}

stop() {
if [ $UID -ne 0 ] ; then
echo "User has sudo privilege."
exit 4
fi
echo -n $"Stopping $prog: "
[ -f $exec_dir/stop.sh ] || exit 2
[ -x $exec_dir/stop.sh ] || exit 2
$exec_dir/stop.sh
retval=$?
}

restart() {
stop
start
}

case "$1" in
start)
$1
;;
stop)
$1
;;
restart)
$1
;;
*)
echo $"Usage: $0 {start|stop|restart}"
exit 2
esac
exit $?
