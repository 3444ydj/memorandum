@echo off
title Unpack File
set userPath=%cd%\environment

%userPath%\7z\7z x %userPath%\jre.zip  -y -o%userPath% && mkdir %userPath%\back && move %userPath%\jre.zip %userPath%\back

echo.
echo -------completed!--------
choice /t 2 /d y /n >nul



