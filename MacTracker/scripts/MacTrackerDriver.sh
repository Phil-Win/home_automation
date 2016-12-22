#!/bin/bash
x=0
while [ $x -lt 255 ]; do
    ping -c 1 192.168.2.$x &
    x=$(expr $x + 1)
done

sleep 30s
# Wait a few seconds for the pings above to finish
arp -a