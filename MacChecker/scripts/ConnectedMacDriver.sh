#!/bin/bash

ssh root@192.168.2.1 /bin/sh << EOF > tmp.txt
  awk '{print \$3}' /tmp/dnsmasq.leases | while read i; do ping -c 1 -wait 1 \$i; done &> /dev/null
  arp -a
EOF
