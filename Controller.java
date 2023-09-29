//Software Engineering Team #7
//Controller File for Laser Tag Project

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Controller implements ActionListener, MouseListener, KeyListener 
{
    View view;
    Model model;

	Controller(Model m) 
	{
        this.model = m;
        DBController DB = new DBController();
        DB.update();


    }

    void setView(View v) 
	{
        this.view = v;
    }

	public void actionPerformed(ActionEvent e) 
	{
        //this.view.removeButton();
    }

    public void mousePressed(MouseEvent e) 
    {
    }

    public void mouseReleased(MouseEvent e) 
    {
    }

    public void mouseEntered(MouseEvent e) 
	{
    }

    public void mouseExited(MouseEvent e) 
	{
    }

	public void mouseClicked(MouseEvent e) 
    {
    }

    public void keyPressed(KeyEvent e) 
	{
        switch (e.getKeyCode()) 
		{
		}
    }





	public void keyReleased(KeyEvent e) 
	{
        switch (e.getKeyCode()) 
		{
            case 27:
                System.exit(0);
            case 81:
                System.exit(0);
        }

    }

    public void keyTyped(KeyEvent e) 
	{
    }

    void update() 
	{
    }
}