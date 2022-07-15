# DualBLDCDriverMagistralV2Gui
Sinusoidal Dual BLDC Driver Magistral V2 Programming and Settling GUI
This package is simply created for adjusting and real-time monitoring of [Dual BLDC Driver Magistral V2] (https://sinusoidal.com.tr/magistralv2/) over USB communication.

# Contains
- This package contains software interface source and header files to communicate Dual BLDC Driver Magistral V2 hardware.
- Package contains,
    - Communication Driver as Uart.java, 
    - Communication package structure as Datapackage.java, 
    - Checksum Calculation for Stm32 as STM32CRC.java, 
    - Graphical User Interface as Gui.java

# Hardware Connection Diagram
- Dual BLDC Driver (Magistral V2)  Connection Diagram ![MagistralV2Connection](https://cloud.sinusoidal.com.tr/f/db977981733e418a8f1d/?dl=1)

# Software Connection Requirements
- Java 1.8.0 or Higher
- For Linux, no requirements, ensure user added to dialout group sudo usermod -a -G dialout username
- For Windows, install necessary driver from [USB Driver] (https://cloud.sinusoidal.com.tr/f/8070805a63274a3682e8/?dl=1) 

# Screenshots
- GUI Screenshot ![GUIScreenShot](https://cloud.sinusoidal.com.tr/f/c178b46ef8154e2ba752/?dl=1)

# Binaries
- Executable Jar binary [JARBinary] (https://cloud.sinusoidal.com.tr/f/ee253eb1c74645cd9881/?dl=1) 
- Executable Windows binary [WindowsBinary] (https://cloud.sinusoidal.com.tr/f/c219cfb8d1c94010baaf/?dl=1) 



