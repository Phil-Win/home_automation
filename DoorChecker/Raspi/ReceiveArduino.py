import RPi.GPIO as GPIO
import sys
sys.path.insert(0, '/e/project/home_automation/lib')
from lib_nrf24 import NRF24
import time
import spidev
import os

GPIO.setmode (GPIO.BCM)

pipes = [[0xE8, 0xE8, 0xF0, 0xF0, 0xE1], [0xF0, 0xF0, 0xF0, 0xF0, 0xE1]]

radio = NRF24(GPIO,spidev.SpiDev())
radio.begin(0, 17)

radio.setPayloadSize(32)
radio.setChannel(0x76)
radio.setDataRate(NRF24.BR_1MBPS)
radio.setPALevel(NRF24.PA_MIN)

radio.setAutoAck(True)
radio.enableDynamicPayloads()
radio.enableAckPayload()

radio.openReadingPipe(1, pipes[1])
radio.printDetails()
radio.startListening()
acceptableReceiveMessages = ["DoorArduino", "DoorStatus"]

while True:
	while not radio.available(0):
		time.sleep(1/100)
	receiveMessage= []
	radio.read(receiveMessage, radio.getDynamicPayloadSize())
	messageRaw =""
	messageType =""
	message = ""
	fileName = ""
	currentDate = time.strftime("%Y-%m-%d")
	currentTime = time.strftime("%X")
	for n in receiveMessage:
		if (n >=32 and n <=126):
			messageRaw+= chr(n)
	messageType = messageRaw.split(',')[0]
	message = messageRaw+","+currentDate+","+currentTime

	if messageType not in acceptableReceiveMessages:
		continue
	
	f =open('/e/project/landingZone/{}/{}.csv'.format(messageType,currentDate), 'a')
	f.write('%s\n' % message)
	f.close()