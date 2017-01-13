#!/bin/bash

source /e/project/home_automation/dynamic_properties/PlayRandomMusic.properties

currentTime=$(date +%s)

if [ $(($currentTime - $lastSongTime)) -gt 300 ]; then
	omxplayer $(ls -1 /e/project/theme_music/*/* | sort -R | head -n 1)
	sed -i "/lastSongTime/c\lastSongTime=$currentTime" /e/project/home_automation/dynamic_properties/PlayRandomMusic.properties
fi