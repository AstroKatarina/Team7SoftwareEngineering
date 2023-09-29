//Software Engineering Team #7
//Game File for Laser Tag Project

import java.awt.Toolkit;
import javax.swing.JFrame;

public class Game extends JFrame 
{
    Model model = new Model();
    Controller controller;
    View view;

    public Game() 
	{
		this.controller = new Controller(this.model);
        this.view = new View(this.controller, this.model);
        this.setTitle("Sprint 2");
        this.setSize(1200, 700);
        this.setFocusable(true);
        this.getContentPane().add(this.view);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.addKeyListener(this.controller);
        this.view.addMouseListener(this.controller);
	}

	public void run() 
	{
        while(true) {
            this.controller.update();
            this.model.update();
            this.view.repaint();
            Toolkit.getDefaultToolkit().sync();

            try {
                Thread.sleep(40L);
            } catch (Exception var2) {
                var2.printStackTrace();
                System.exit(1);
            }
        }
    }

	public static void main(String[] args) 
	{
        Game g = new Game();
        g.run();
    }
}