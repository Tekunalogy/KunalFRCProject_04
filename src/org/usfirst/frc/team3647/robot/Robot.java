package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	
	Joysticks controller = new Joysticks();
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		controller.updateMainController();
		System.out.println(Joysticks.rightJoyStickx);
		testFunctions();
		if(getControllerDegrees() < 135 && getControllerDegrees() > 45)
		{
			moveForward(getMagnitude());
		}
		else if(getControllerDegrees() < 315 && getControllerDegrees() > 225)
		{
			moveForward(getMagnitude());
		}
		else if(getControllerDegrees() < 225 && getControllerDegrees() > 135)
		{
			moveLeft(getMagnitude());
		}
		else if(getControllerDegrees() < 45 && getControllerDegrees() > 315)
		{
			moveRight(getMagnitude());
		}
		
	}
	
	public static void moveForward(double speed)
	{
		Motors.leftTalon.set(speed);
		Motors.rightTalon.set(-speed);
	}
	public static void moveBackward(double speed)
	{
		Motors.leftTalon.set(-speed);
		Motors.rightTalon.set(speed);
	}
	public static void moveRight(double speed)
	{
		Motors.leftTalon.set(speed);
		Motors.rightTalon.set(speed);
	}
	public static void moveLeft(double speed)
	{
		Motors.leftTalon.set(-speed);
		Motors.rightTalon.set(-speed);
	}
	public static double getControllerDegrees()
	{
		if(Joysticks.rightJoySticky > 0  && Joysticks.rightJoyStickx > 0) //1st quadrant
		{
			return ( 270 + Math.toDegrees(Math.atan(Joysticks.rightJoyStickx/Joysticks.rightJoySticky)));
		}
		if(Joysticks.rightJoySticky > 0  && Joysticks.rightJoyStickx < 0)
		{
			return 180- Math.toDegrees(Math.atan(Joysticks.rightJoySticky/Joysticks.rightJoyStickx));
		}
		if(Joysticks.rightJoySticky < 0  && Joysticks.rightJoyStickx < 0)
		{
			return 180- Math.toDegrees(Math.atan(Joysticks.rightJoySticky/Joysticks.rightJoyStickx));
		}
		if(Joysticks.rightJoySticky < 0  && Joysticks.rightJoyStickx > 0)
		{
			return -Math.toDegrees(Math.atan(Joysticks.rightJoySticky/Joysticks.rightJoyStickx));
		}
		if(Joysticks.rightJoySticky == 0  && Joysticks.rightJoyStickx < 0)
		{
			return 180;
		}
		if(Joysticks.rightJoySticky == 0  && Joysticks.rightJoyStickx < 0)
		{
			return 0;
		}
		if(Joysticks.rightJoySticky > 0  && Joysticks.rightJoyStickx == 0)
		{
			return 270;
		}
		if(Joysticks.rightJoySticky < 0  && Joysticks.rightJoyStickx == 0)
		{
			return 90;
		}
		else
		{
			return 0;
		}
	}
	
	//This function returns the magnitude of the vector of the right Joystick
	public static double getMagnitude()
	{
		return Math.sqrt(Math.pow(Joysticks.rightJoySticky, 2) + Math.pow(Joysticks.rightJoyStickx, 2));
	}
	
	public void testFunctions()
	{
		double magnitude =  getMagnitude();
		double angle = getControllerDegrees();
		
		System.out.println("Magnitude: " + magnitude + "; " + "Angle: " + angle + ";");
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
		
	}
}

