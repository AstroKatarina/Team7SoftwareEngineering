import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;
import java.util.Timer;

public class Test{

    public static int FrameWidth = 1280, FrameHeight = 720;

    JFrame frame = new JFrame("CardLayout Test");
    JPanel panelContainer = new JPanel();
    JPanel panelStartup;      
    JPanel panelPlayAction = new JPanel();

    //Declare and initialize all Swing Objects for PlayerEntry Panel
    JPanel panelPlayerEntry = new JPanel();
    JButton button1 = new JButton("1 Swap");
    JButton button2 = new JButton("2 Swap");
    CardLayout cl = new CardLayout();
    JTextField[] greenID, greenName, redID, redName;
    JTextField playerEntryTitle = new JTextField("EDIT CURRENT GAME");
    JTextField greenTeamHeading = new JTextField("Green Team");
    JTextField redTeamHeading = new JTextField("Red Team");
    JTextField greenIDHeading = new JTextField("Player ID:");
    JTextField greenNameHeading = new JTextField("Code Name:");
    JTextField redIDHeading = new JTextField("Player ID:");
    JTextField redNameHeading = new JTextField("Code Name:");
    Font titleFont = new Font("Serif",Font.BOLD,30);
    Color playerEntryBackgroundColor = new Color(200,205,210);
    

    private static BufferedImage image;
	//scale startup image method
    private static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(src, 0, 0, w, h, null);
        g.dispose();
        return img;
    }

    public Test() {    
        

        try {
            image = ImageIO.read(new File("logo.jpg"));
            BufferedImage scaledImage = scale(image, 1200, 700);

            panelStartup = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), null);
                }
            };

            

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }

        
        
        //Setup the panels for PlayerEntry screen
        panelContainer.setLayout(cl);
        panelPlayerEntry.setLayout(null);
        panelPlayAction.setLayout(null);
        panelPlayerEntry.setBackground(playerEntryBackgroundColor);

        //Add buttons to panels !!!TEMPORARY TESTING, SHOULD BE REMOVED!!!
        button1.setBounds(FrameWidth/2-50,FrameHeight-60,100,30);
        button2.setBounds(FrameWidth/2-50,FrameHeight-60,100,30);
        panelPlayerEntry.add(button1);
        panelPlayAction.add(button2);

        //Add panels to the container panel set up with CardLayout
        panelContainer.add(panelStartup,"0");
        panelContainer.add(panelPlayerEntry,"1");
        panelContainer.add(panelPlayAction,"2");

        //Choose which panel to display on opening
        cl.show(panelContainer,"0");

        //Action listeners for the buttons !!!CAN ALSO BE REMOVED!!!
        button1.addActionListener(e -> cl.show(panelContainer,"2"));
        button2.addActionListener(e -> cl.show(panelContainer,"1"));

        //Setup the frame and add the container panel to it
        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(FrameWidth, FrameHeight);
        frame.setVisible(true);



        


        //Set up the Player Entry Title
        playerEntryTitle.setBounds(0,10,FrameWidth,60);
        playerEntryTitle.setEditable(false);
        playerEntryTitle.setHorizontalAlignment(JTextField.CENTER);
        playerEntryTitle.setBackground(null);
        playerEntryTitle.setBorder(new LineBorder(Color.WHITE,0));
        playerEntryTitle.setFont(titleFont);
        playerEntryTitle.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(playerEntryTitle);


        //Initialization of arrays for TextFields
        greenID = new JTextField[15];
        greenName = new JTextField[15];
        redID = new JTextField[15];
        redName = new JTextField[15];

        //Setup variables for position of JTextFields on PlayerEntry screen
        int width = 150, height = 25, verticalSpacing = 3, horizontalSpacing = 2, startX = FrameWidth/2-(2*width)-horizontalSpacing, startY = 150, x = startX, y = startY;

        //Setup JTextField for the heading above the green team side
        greenTeamHeading.setBounds(x+width/2+horizontalSpacing/2,y-(height+40)-verticalSpacing,width,height+10);
        greenTeamHeading.setEditable(false);
        greenTeamHeading.setHorizontalAlignment(JTextField.CENTER);
        greenTeamHeading.setBackground(new Color(0,200,0));
        greenTeamHeading.setBorder(new LineBorder(Color.WHITE,2));
        panelPlayerEntry.add(greenTeamHeading);


        //Set up Green team ID column
        greenIDHeading.setBounds(x,y-height-verticalSpacing,width,height);
        greenIDHeading.setEditable(false);
        greenIDHeading.setHorizontalAlignment(JTextField.CENTER);
        greenIDHeading.setBackground(null);
        greenIDHeading.setBorder(new LineBorder(Color.WHITE,0));
        greenIDHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(greenIDHeading);
        for(int i = 0; i<15; i++)
        {
            greenID[i] = new JTextField();
            greenID[i].setBounds(x,y,width,height);
            greenID[i].setBorder(new LineBorder(Color.BLACK,1));
            greenID[i].setHorizontalAlignment(JTextField.CENTER);
            greenID[i].setBackground(Color.WHITE);
            greenID[i].setForeground(Color.BLACK);
            panelPlayerEntry.add(greenID[i]);

            y+= height + verticalSpacing;
        }

        x += width + horizontalSpacing;
        y = startY;

        //Set up Green team Name column
        greenNameHeading.setBounds(x,y-height-verticalSpacing,width,height);
        greenNameHeading.setEditable(false);
        greenNameHeading.setHorizontalAlignment(JTextField.CENTER);
        greenNameHeading.setBackground(null);
        greenNameHeading.setBorder(new LineBorder(Color.WHITE,0));
        greenNameHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(greenNameHeading);
        for(int i = 0; i<15; i++)
        {
            greenName[i] = new JTextField();
            greenName[i].setBounds(x,y,width,height);
            greenName[i].setBorder(new LineBorder(Color.BLACK,1));
            greenName[i].setEditable(false);
            greenName[i].setBackground(Color.WHITE);
            greenName[i].setForeground(Color.BLACK);
            panelPlayerEntry.add(greenName[i]);

            y+= height + verticalSpacing;
        }

        x += width + horizontalSpacing*4;
        y = startY;
        
        redTeamHeading.setBounds(x+width/2+horizontalSpacing/2,y-(height+40)-verticalSpacing,width,height+10);
        redTeamHeading.setEditable(false);
        redTeamHeading.setHorizontalAlignment(JTextField.CENTER);
        redTeamHeading.setBackground(new Color(200,0,0));
        redTeamHeading.setBorder(new LineBorder(Color.WHITE,2));
        panelPlayerEntry.add(redTeamHeading);
        
        //Set up Red team ID column
        redIDHeading.setBounds(x,y-height-verticalSpacing,width,height);
        redIDHeading.setEditable(false);
        redIDHeading.setHorizontalAlignment(JTextField.CENTER);
        redIDHeading.setBackground(null);
        redIDHeading.setBorder(new LineBorder(Color.WHITE,0));
        redIDHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(redIDHeading);
        for(int i = 0; i<15; i++)
        {
            redID[i] = new JTextField();
            redID[i].setBounds(x,y,width,height);
            redID[i].setBorder(new LineBorder(Color.BLACK,1));
            redID[i].setBackground(Color.WHITE);
            redID[i].setForeground(Color.BLACK);
            redID[i].setHorizontalAlignment(JTextField.CENTER);
            panelPlayerEntry.add(redID[i]);

            y+= height + verticalSpacing;
        }

        x += width + horizontalSpacing;
        y = startY;

        //Set up Red team Name column   
        redNameHeading.setBounds(x,y-height-verticalSpacing,width,height);
        redNameHeading.setEditable(false);
        redNameHeading.setHorizontalAlignment(JTextField.CENTER);
        redNameHeading.setBackground(null);
        redNameHeading.setBorder(new LineBorder(Color.WHITE,0));
        redNameHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(redNameHeading);
        for(int i = 0; i<15; i++)
        {
            redName[i] = new JTextField();
            redName[i].setBounds(x,y,width,height);
            redName[i].setBorder(new LineBorder(Color.BLACK,1));
            redName[i].setEditable(false);
            redName[i].setBackground(Color.WHITE);
            redName[i].setForeground(Color.BLACK);
            panelPlayerEntry.add(redName[i]);

            y+= height + verticalSpacing;
        }
    }





    public static void main(String[] args){
        
        Test test = new Test();  

        TimerTask task = new TimerTask() {
            public void run() {
                test.cl.show(test.panelContainer,"1");
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);



        //All GUI updates should happen under run
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

               /*Rectangle r = test.frame.getBounds();
                Test.FrameWidth = r.width;
                Test.FrameHeight = r.height;*/
                test.frame.repaint();
            }
        });
    }

    

    
}