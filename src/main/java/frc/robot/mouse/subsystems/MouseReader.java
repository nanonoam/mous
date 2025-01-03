// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.mouse.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.mouse.MouseConstants.*;

public class MouseReader extends SubsystemBase {
    private SerialPort mousePort;
    private byte[] mouseData = new byte[4];
    private int rawX;
    private int rawY;
    
    
    public MouseReader() {
        try {
            mousePort = new SerialPort(1200, Port.kUSB1);
            mousePort.setReadBufferSize(4);
            mousePort.setTimeout(0.1);
            mousePort.setWriteBufferSize(4);
        } catch (Exception e) {
            System.err.println("Failed to initialize mouse: " + e.getMessage());
        }
    }
    
    @Override
    public void periodic() {
        if (mousePort != null && mousePort.getBytesReceived() >= 4) {
            try {
                mouseData = mousePort.read(4);
                rawX = (byte)mouseData[1];
                rawY = (byte)mouseData[2];
            } catch (Exception e) {
                rawX = 0;
                rawY = 0;
                System.err.println("Error reading mouse data: " + e.getMessage());
            }
        }
    }
    
    // Get position in meters
    public double getXMeters() {
        return rawX * COUNTS_TO_METERS;
    }
    
    public double getYMeters() {
        return rawY * COUNTS_TO_METERS;
    }
    
    // Get raw counts
    public int getRawX() {
        return rawX;
    }
    
    public int getRawY() {
        return rawY;
    }
    
    public void close() {
        if (mousePort != null) {
            mousePort.close();
        }
    }
    
}