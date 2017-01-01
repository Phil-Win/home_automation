Based off the NRF204 Hello world project

Arduino will instead send a door status based whenever an interupt on pin 2 occurs.
Aside from that, it will send a Door status: working message every 15 minutes

The raspi will listen for radio messages, interpret the data ->store it -> run something else based on the message