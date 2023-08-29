@echo off
title zip-package
set userPath=..\environment

REM package
rem call mvn clean install -Dmaven.test.skip=true

rem del file
rmdir MEMORANDUM /s /q

rem create README.txt
mkdir MEMORANDUM

copy /y .\start.bat MEMORANDUM
copy /y ..\reset-environment.bat MEMORANDUM
copy /y ..\set-environment.bat MEMORANDUM
copy /y .\README.txt MEMORANDUM
xcopy /y .\target\*.jar  MEMORANDUM\ /s
xcopy /y ..\environment  MEMORANDUM\environment /s /i

%userPath%\7z\7z  a MEMORANDUM\MEMORANDUM.zip MEMORANDUM
echo.
echo -------completed!--------
choice /t 2 /d y /n >nul