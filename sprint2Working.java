import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class sprint2Working 
{
	//global variables
    private static BufferedImage image;
	static boolean showCard1 = true;
	private static BufferedImage backgroundImage;
	//scale startup image method
    private static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(src, 0, 0, w, h, null);
        g.dispose();
        return img;
    }
    
    
	//main method
    public static void main(String[] args) 
	{
		//get frame on startup of program
        JFrame frame = new JFrame("Timer CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
		//create cardPanel to hold startup card, player entry screen card, and more.
        JPanel cardPanel = new JPanel(new CardLayout());
		//adds scaled image to card 1
        try {
            image = ImageIO.read(new File("logo.jpg"));
            BufferedImage scaledImage = scale(image, 1200, 700);

            JPanel card1 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), null);
                }
            };
            cardPanel.add(card1, "card1");

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
		//create the player entry screen card
        //JPanel card2 = new JPanel();
        


		JPanel card2 = new JPanel() 
		{
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
						
                    // Load your image
                    try {
                        backgroundImage = ImageIO.read(new File("playerEntryScreenImage.jpg"));
						BufferedImage scaledBackground = scale(backgroundImage, 1200, 700);
                        // Draw the image on the panel
                        g.drawImage(scaledBackground, 0, 0, getWidth(), getHeight(), this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            //card2.setLayout();

			//card2.add(new JLabel("card2"));

		//create table that needs to go on player entry screen card
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Green Team Player ID");
        tableModel.addColumn("Green Team Name");
        for (int i = 0; i < 16; i++)
        {
            tableModel.addRow(new Object[]{"Player " + (i + 1) + " ID", "Player " + (i + 1) + " name"});
        }

        
        JTable table1 = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);
        card2.add(scrollPane);

		/*JLabel clearEntries = new JLabel("To Clear Entries Press F12");
		card2.add(clearEntries);
		JLabel nextScreen = new JLabel("To Go To Play Screen Press F5");
		card2.add(nextScreen);*/

		DefaultTableModel tableModel2 = new DefaultTableModel();
        tableModel2.addColumn("Red Team Player ID");
        tableModel2.addColumn("Red Team Name");
        for (int i = 0; i < 16; i++)
        {
            tableModel2.addRow(new Object[]{"Player " + (i + 1) + " ID", "Player " + (i + 1) + ""});
        }

        
        JTable table2 = new JTable(tableModel2);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        card2.add(scrollPane2);

		

        cardPanel.add(card2, "card2");

        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "card1");

		//timer that switches start up image card to player entry table card
        Timer timer = new Timer(3000, new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cardLayout.show(cardPanel, "card2");
            }
        });
        timer.start();
		//shows the cards in cardPanel to the frame
        frame.add(cardPanel);
        frame.setVisible(true);
    }
}
