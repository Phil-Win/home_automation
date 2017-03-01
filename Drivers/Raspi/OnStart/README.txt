These will be lines to add to /etc/rc.local in order to run various projects on startup.

su homeprx -c 'sudo python3 /e/project/home_automation/projects/DoorChecker/ReceiveArduino.py >> /e/tmp/logs/receiveArduino.logs 2>&1 &'

changes to file /etc/fstab
Goal: to mount usb on startup

UUID=08F1-D853 /e/project vfat uid=homeprx,gid=admin 0 0

cron jobs:crontab -e for root
*/15 * * * * su homeprx -c '/e/project/home_automation/projects/MacChecker/ConnectedMacDriver.sh &'
@reboot su homeprx -c '/e/project/home_automation/projects/MacChecker/ConnectedMacDriver.sh &'

0 20 * * * su homeprx -c '/e/project/Hadoop/Stocks/LandSymbols/Scripts/Stocks_LandSymbols.sh &'
@reboot su homeprx -c '/e/project/Hadoop/Stocks/LandSymbols/Scripts/Stocks_LandSymbols.sh &'