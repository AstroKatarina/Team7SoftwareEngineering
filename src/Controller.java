//Software Engineering Team #7
//Controller File for Laser Tag Project

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

class Controller implements ActionListener, MouseListener, KeyListener 
{
    View view;
    Model model;
    DBController dbController;
    MusicPlayer musicPlayer;
    Thread serverThread;

	Controller(Model m, DBController db, MusicPlayer mp) 
	{
        this.model = m;
        this.dbController = db;
        this.musicPlayer = mp;
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
                    musicPlayer.startPlayingWAV();

                    break;
                case KeyEvent.VK_F1:
                    model.printOut();
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
        if (View.firstCountDownTimer != null && !View.firstCountDownTimer.isRunning()) {
            View.firstCountDownTimer.start();
        }
        Photon.action = true;
        view.cl.show(view.panelContainer,"2");
        view.setPlayersList();
        view.setupPlayActionPlayers();

        // Create instance of UDPServer
        System.out.println("Creating server");
        UDPServer udpServer = new UDPServer();

        // Create a thread for the UDPServer
        serverThread = new Thread(() -> {
            try {
                udpServer.startServer();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Caught error");
            }
        });

        // Thread should run and execute startServer in UDPServer
        serverThread.start();
    }

    public void endGame()
    {
        Photon.action = false;
        try {
            UDPClient.sendData(221);
            UDPClient.sendData(221);
            UDPClient.sendData(221);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        view.restartPopUpFrame.setVisible(true);
        view.restartPopUpFrame.toFront();
        
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
        view.tempPlayersList.clear();
    }

    private void actionClearEntries()
    {
        view.setupPlayActionPlayers();
        View.eventTextArea.setText("");
    }

    public void setID(int ID){
        model.setPlayerID(ID);
    }

    public void setcodeName(String codeName){
        model.setPlayerCodeName(codeName);
    }

     public void setTeam(int parallelIndex){
        if((parallelIndex >=0 && parallelIndex <= 14))
        {
            model.setPlayerTeam(0);
        }
        else
        {
            model.setPlayerTeam(1);
        }
    }

    public void setScore(int Score){
        model.setPlayerScore(Score);
    }

     public void setEquipmentID(int EquipmentID){
        model.setPlayerEquipmentID(EquipmentID);
    }

    public void addModelPlayer(Player player)
    {
        model.addPlayer(player);
    }

    public ArrayList<Player> getModelPlayerList()
    {
        return model.getPlayerList();
    }

    public void updateStuff()
    {
        model.sortPlayerScores();
        view.reorderScoreboard();
    }
    
    public void restartGame()
    {
        Model.Players.clear();
        if (View.gameLoop != null && View.gameLoop.isRunning()) {
            View.gameLoop.stop();
        }
        if(serverThread != null && serverThread.isAlive())
        {
            serverThread.interrupt();
        }
        clearEntries();
        actionClearEntries();
        view.cl.show(view.panelContainer,"1");
        view.playerIDFields[0].requestFocus();
        
    }
}
