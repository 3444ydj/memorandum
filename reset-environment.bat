@echo off
title reset env

set userPath=%cd%\environment

move /y %userPath%\back\jre.zip %userPath% && rmdir /s /q %userPath%\back %userPath%\jre

echo.
echo -------completed!--------
choice /t 1 /d y /n >nul