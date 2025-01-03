// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.mouse;

/** Add your docs here. */
public class MouseConstants {
    // Mouse DPI (adjust based on your mouse specifications)
    public static final int MOUSE_DPI = 1721;

    // Conversion constants
    public static final double INCHES_TO_METERS = 0.0254;
    public static final double COUNTS_TO_METERS = INCHES_TO_METERS / MOUSE_DPI;
}
