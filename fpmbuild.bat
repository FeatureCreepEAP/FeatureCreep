@echo off
setlocal EnableExtensions

rem fpmbuild-version=0.0.1
rem Maven Central release used by this repository.
set "FPMBUILD_VERSION_ENV=%FPMBUILD_VERSION%"
set "fpmbuild-version=0.0.1"
set "FPMBUILD_VERSION=%fpmbuild-version%"
if not "%FPMBUILD_VERSION_ENV%"=="" set "FPMBUILD_VERSION=%FPMBUILD_VERSION_ENV%"

set "SCRIPT_DIR=%~dp0"
if "%SCRIPT_DIR:~-1%"=="\" set "SCRIPT_DIR=%SCRIPT_DIR:~0,-1%"

set "JAVA_EXE=java"
if not "%JAVA_HOME%"=="" set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
"%JAVA_EXE%" -version >nul 2>&1
if errorlevel 1 (
    echo FPMBuild requires Java 25+. Set JAVA_HOME or put java.exe on PATH. 1>&2
    exit /b 1
)

if "%M2_REPOSITORY%"=="" set "M2_REPOSITORY=%USERPROFILE%\.m2\repository"
set "FPMBUILD_GROUP_PATH=com\asbestosstar\fpmbuild-java"
set "FPMBUILD_JAR=%M2_REPOSITORY%\%FPMBUILD_GROUP_PATH%\%FPMBUILD_VERSION%\fpmbuild-java-%FPMBUILD_VERSION%.jar"
set "FPMBUILD_GROUP_URL=com/asbestosstar/fpmbuild-java"
if "%FPMBUILD_REPOSITORY%"=="" set "FPMBUILD_REPOSITORY=https://repo1.maven.org/maven2"
set "FPMBUILD_URL=%FPMBUILD_REPOSITORY%/%FPMBUILD_GROUP_URL%/%FPMBUILD_VERSION%/fpmbuild-java-%FPMBUILD_VERSION%.jar"
set "BOOTSTRAP_SOURCE=%SCRIPT_DIR%\tools\FPMBuildMavenCentralBootstrap.java"

if not exist "%FPMBUILD_JAR%" (
    echo FPMBuild %FPMBUILD_VERSION% is not in the local Maven repository. 1>&2
    echo Downloading from Maven Central to: %FPMBUILD_JAR% 1>&2
    "%JAVA_EXE%" "%BOOTSTRAP_SOURCE%" "%FPMBUILD_URL%" "%FPMBUILD_JAR%"
    if errorlevel 1 exit /b %errorlevel%
)

rem Preserve classic tutorial use: fpmbuild.bat -ba SPECS\examplemod.spec
if /I "%~1"=="-ba" (
    "%JAVA_EXE%" -jar "%FPMBUILD_JAR%" "%SCRIPT_DIR%" %*
) else (
    "%JAVA_EXE%" -jar "%FPMBUILD_JAR%" %*
)
exit /b %errorlevel%
