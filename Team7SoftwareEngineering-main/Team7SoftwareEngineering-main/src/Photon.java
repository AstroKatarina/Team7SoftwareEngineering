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
