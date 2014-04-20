#!/bin/bash
# usage: switchValve open|close

COMMAND=$1
PIBLASTERVALVEPIN=2
USAGE='usage of switchValve.sh: switchValve open|close'

function openValve {
	echo "$PIBLASTERVALVEPIN=1" > /dev/pi-blaster
	sleep 1
	echo "$PIBLASTERVALVEPIN=0.5" > /dev/pi-blaster
}

function closeValve {
	echo "$PIBLASTERVALVEPIN=0" > /dev/pi-blaster
}

if [ "$COMMAND" = "open" ]; then
	openValve
elif [ "$COMMAND" = "close" ]; then
	closeValve
else
	echo "$USAGE"
fi

