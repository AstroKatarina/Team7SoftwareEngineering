//Software Engineering Team #7
//Game File for Laser Tag Project

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public class Photon 
{
    Model model = new Model();
    Controller controller;
    View view;
    DBController dbController = new DBController();

    public Photon() 
	{

		this.controller = new Controller(this.model, this.dbController);
        view = new View(this.controller);
        
	}


	public static void main(String[] args){
        
        System.out.println("Creating server");
        // Create an instance of UDPServe
        UDPServer udpServer = new UDPServer();

        // Create a thread for the UDPServer
        Thread serverThread = new Thread(udpServer);

        // Start the thread
        serverThread.start();

        try {
            // Command to execute the Python script
            String pythonScriptPath = "C:\Users\evanb\OneDrive\Documents\SoftwareEngineering\Team7SoftwareEngineering\src\trafficgenarator_v2.py";
            String[] command = {"python", pythonScriptPath};

            // Start the process
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            // Wait for the process to finish (optional)
            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Photon photon = new Photon();

        TimerTask task = new TimerTask() {
            public void run() {
                photon.view.cl.show(photon.view.panelContainer,"1");
                photon.view.playerIDFields[0].requestFocus();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);



        //All GUI updates should happen under run
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

               /*Rectangle r = test.frame.getBounds();
                Test.frameWidth = r.width;
                Test.frameHeight = r.height;*/
                photon.view.frame.repaint();
                photon.view.popUpFrame.repaint();
            }
        });
    }
}
