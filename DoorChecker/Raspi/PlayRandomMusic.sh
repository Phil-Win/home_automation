#!/bin/bash

source /e/project/home_automation/dynamic_properties/PlayRandomMusic.properties
currentTime=$(date +%s)

if [ -e /e/project/home_automation/dynamic_properties/PlayRandomMusic.lock ]; then
	exit 1;
fi

touch /e/project/home_automation/dynamic_properties/PlayRandomMusic.lock;

if [ $(($currentTime - $lastSongTime)) -gt 300 ]; then
	omxplayer $(ls -1 /e/project/theme_music/*/* | sort -R | head -n 1)
	sed -i "/lastSongTime/c\lastSongTime=$currentTime" /e/project/home_automation/dynamic_properties/PlayRandomMusic.properties
fi

rm /e/project/home_automation/dynamic_properties/PlayRandomMusic.lock;