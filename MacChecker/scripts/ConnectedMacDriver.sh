#!/bin/bash


#getting properties to use
source /e/project/home_automation/dynamic_properties/MacChecker.properties;
currentTime=$(date +%T)
currentDate=$(date +%Y-%m-%d)

#ping all ip's that have leases on router... then getting the arp table from router -> piping it all to a temporary file
ssh ${routerSSHAddress} /bin/sh << EOF > ${tmpArpOutput}
  #awk '{print \$3}' /tmp/dnsmasq.leases | while read i; do ping -c 1 -wait 2 \$i; done &> /dev/null
  arp -a | awk -v OFS=, '{print \$1,\$2,\$4,"$currentDate,$currentTime"}'
EOF

#landing the data into the daily landing zone
cat ${tmpArpOutput} >> ${landingZoneFolder}/${currentDate}.csv