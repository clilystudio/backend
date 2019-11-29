@ECHO OFF
CLS

ECHO --------------------------------------------------------------------------
ECHO -- WistLotto BackEnd Publish                                           ---
ECHO --------------------------------------------------------------------------

cmd /c .\gradlew.bat build --warning-mode=all
COPY /Y .\build\libs\wistlotto2020-2.0.20.jar C:\wistlotto\wistlotto2020.jar

ECHO.
ECHO Publish Finish!
ECHO ==========================================================================

git archive -o ..\backend.zip HEAD
