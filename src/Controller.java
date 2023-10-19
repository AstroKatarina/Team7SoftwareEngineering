//Software Engineering Team #7
//Controller File for Laser Tag Project

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

class Controller implements ActionListener, MouseListener, KeyListener 
{
    View view;
    Model model;
    DBController dbController;

	Controller(Model m, DBController db) 
	{
        this.model = m;
        this.dbController = db;
        
    }

    void setView(View v) 
	{
        this.view = v;
        view.panelPlayerEntry.addKeyListener(this);
        for(JTextField field : view.playerIDFields)
        {
            field.addKeyListener(this);
        }
        for(JTextField field : view.playerNameFields)
        {
            field.addKeyListener(this);
        }
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
           int key = e.getKeyCode();
           switch(key)
           {
                case KeyEvent.VK_F12:
                    clearEntries();
                    break;
                case KeyEvent.VK_F5:
                    startGame();
                    break;
           }
        
                

    }

    public void keyTyped(KeyEvent e) 
	{
    }

    void update() 
	{
    }

    public String queryHandoff(int playerID)
    {
        String codename = dbController.queryForCodename(playerID);
        return codename;
    }

    public void insertCodename(int id, String codename)
    {
        dbController.insertNewRow(id, codename);
    }

    private void startGame()
    {
        view.cl.show(view.panelContainer,"3");
    }

    private void clearEntries()
    {
        for(JTextField field : view.playerIDFields)
        {
            field.setText(null);
        }
        for(JTextField field : view.playerNameFields)
        {
            field.setText(null);
        }
    }
    
}