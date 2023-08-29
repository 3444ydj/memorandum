@echo off
title MEMORANDUM
call reset-environment.bat
call set-environment.bat
set jrePath=%cd%\environment\jre\bin

%jrePath%\java  -jar memorandum-1.0.0.jar
