import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class View{

    public static int frameWidth = 1280, frameHeight = 720;
    public static int popUpFrameWidth = 400, popUpFrameHeight = 200;
    
    private int nextIDField, currentID;

    Controller controller;

    JFrame frame = new JFrame("CardLayout Test");
    JPanel panelContainer = new JPanel();
    JPanel panelPlayAction = new JPanel();
    
    //Declare objects for StartUp panel
    JPanel panelStartup;   
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
    

    //Declare and initialize all Swing Objects for PlayerEntry Panel
    JPanel panelPlayerEntry = new JPanel();
    CardLayout cl = new CardLayout();
    JTextField[] playerIDFields, playerNameFields;
    JTextField playerEntryTitle = new JTextField("EDIT CURRENT GAME");
    JTextField greenTeamHeading = new JTextField("Green Team");
    JTextField redTeamHeading = new JTextField("Red Team");
    JTextField greenIDHeading = new JTextField("Player ID:");
    JTextField greenNameHeading = new JTextField("Code Name:");
    JTextField redIDHeading = new JTextField("Player ID:");
    JTextField redNameHeading = new JTextField("Code Name:");
    Font titleFont = new Font("Serif",Font.BOLD,30);
    Color playerEntryBackgroundColor = new Color(200,205,210);
    //Delcare and initialize objects for the EID Prompt Popup
    JFrame popUpFrame = new JFrame("Enter Equipment ID");
    JPanel panelPromptPopUp = new JPanel();
    JTextField enterEID = new JTextField();
    JTextField entryPrompt = new JTextField();
    

    

    public View(Controller c) {    
        
        controller = c;

        //Setup the startup panel with the logo image
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

        
        
        //Setup the panels
        panelContainer.setLayout(cl);
        panelPlayerEntry.setLayout(null);
        panelPlayAction.setLayout(null);
        panelPromptPopUp.setLayout(null);
        panelPromptPopUp.setBackground(playerEntryBackgroundColor);
        panelPlayerEntry.setBackground(playerEntryBackgroundColor);

        //Add panels to the container panel set up with CardLayout
        panelContainer.add(panelStartup,"0");
        panelContainer.add(panelPlayerEntry,"1");
        panelContainer.add(panelPlayAction,"2");

        //Choose which panel to display on opening
        cl.show(panelContainer,"0");

        //Setup the main frame and add the container panel to it
        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Setup the popup frame and add the panel to it
        popUpFrame.add(panelPromptPopUp);
        popUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUpFrame.setSize(popUpFrameWidth, popUpFrameHeight);
        popUpFrame.setLocationRelativeTo(frame);
        popUpFrame.setVisible(false);



        //Define Action Listener for PopUp Entry Field
        ActionListener listenerEIDEntry = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Object obj = e.getSource();
                    if (obj instanceof JTextField) {
                        JTextField field = (JTextField)obj;
                        
                        try{
                            
                            int eid = Integer.parseInt(field.getText());
                            System.out.println("Equipment ID: " + eid);
                            UDPClient.sendData(eid);
                            
                            field.setBorder(new LineBorder(Color.BLACK,1));
                            field.setText(null);
                            popUpFrame.setVisible(false);

                            if(nextIDField<playerIDFields.length){
                                playerIDFields[nextIDField].requestFocus();
                            } else {
                                playerIDFields[0].requestFocus();
                        }
                        }
                        catch(NumberFormatException e1) {
                            System.out.println("Invalid Equipment ID. ID must be an integer.");
                            field.setText(null);
                            field.setBorder(new LineBorder(Color.RED,1));
                        }
                        catch(IOException e2) {
                            System.out.println("IOException => " + e2.getMessage());
                        }
                        

                        
                    }
                }
        };

        //Setup the popup panel
        int entryWidth = 50, entryHeight=25;
        enterEID.setBounds(popUpFrameWidth/2-entryWidth/2,popUpFrameHeight/2+entryHeight/2,entryWidth,entryHeight);
        enterEID.setHorizontalAlignment(JTextField.CENTER);
        enterEID.setBorder(new LineBorder(Color.BLACK,1));
        enterEID.addActionListener(listenerEIDEntry);
        panelPromptPopUp.add(enterEID);
    
        entryPrompt.setBounds(0,popUpFrameHeight/2-(2*entryHeight)-10,popUpFrameWidth,entryHeight);
        entryPrompt.setHorizontalAlignment(JTextField.CENTER);
        entryPrompt.setBorder(new LineBorder(Color.WHITE,0));
        entryPrompt.setEditable(false);
        entryPrompt.setBackground(null);
        panelPromptPopUp.add(entryPrompt);


        //Set up the Player Entry Title
        playerEntryTitle.setBounds(0,10,frameWidth,60);
        playerEntryTitle.setEditable(false);
        playerEntryTitle.setHorizontalAlignment(JTextField.CENTER);
        playerEntryTitle.setBackground(null);
        playerEntryTitle.setBorder(new LineBorder(Color.WHITE,0));
        playerEntryTitle.setFont(titleFont);
        playerEntryTitle.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(playerEntryTitle);

        //Define Action Listener for ID Fields
        ActionListener listenerID = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Object obj = e.getSource();
                    if (obj instanceof JTextField) {
                        JTextField field = (JTextField)obj;
                        int parallelIndex = searchFieldsArray(playerIDFields, field);
                        nextIDField = parallelIndex+1;
                        
                        try{
                            int id = Integer.parseInt(field.getText());
                            String codeName = controller.queryHandoff(id);
                            System.out.println("ID: " + field.getText() + " at index " + parallelIndex);
                            field.setBorder(new LineBorder(Color.BLACK,1));
                        if(codeName == null)
                        {
                            currentID = id;
                            playerNameFields[parallelIndex].requestFocus();
                            playerNameFields[parallelIndex].setBorder(new LineBorder(Color.GREEN,1));
                            playerNameFields[parallelIndex].setEditable(true);
                        } else {
                            playerNameFields[parallelIndex].setText(codeName);
                            entryPrompt.setText("Enter " + codeName + "'s equipment ID and press return.");
                            popUpFrame.setVisible(true);
                            popUpFrame.toFront();
                            enterEID.requestFocus();
                            
                        }
                        } catch(NumberFormatException e1 ) {
                            System.out.println("Invalid ID input. Must be an integer.");
                            field.setText(null);
                            field.setBorder(new LineBorder(Color.RED,1));
                        }
                        
                        
                    }
                }
        };

        //Define Action Listener for Name Fields
        ActionListener listenerName = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Object obj = e.getSource();
                    if (obj instanceof JTextField) {
                        JTextField field = (JTextField)obj;
                        System.out.println("Name = " + field.getText());
                        controller.insertCodename(currentID,field.getText());

                        field.setEditable(false);
                        field.setBorder(new LineBorder(Color.BLACK,1));

                        entryPrompt.setText("Enter " + field.getText() + "'s equipment ID and press return.");
                        popUpFrame.setVisible(true);
                        popUpFrame.toFront();
                        enterEID.requestFocus();

                    }
                }
        };

        //Initialization of arrays for TextFields
        playerIDFields = new JTextField[30];
        playerNameFields = new JTextField[30];

        //Setup variables for position of JTextFields on PlayerEntry screen
        int width = 150, height = 25, verticalSpacing = 3, horizontalSpacing = 2, startX = frameWidth/2-(2*width)-horizontalSpacing, startY = 150, x = startX, y = startY;

        //Setup JTextField for the heading above the teams columns
        greenTeamHeading.setBounds(x+width/2+horizontalSpacing/2,y-(height+40)-verticalSpacing,width,height+10);
        greenTeamHeading.setEditable(false);
        greenTeamHeading.setHorizontalAlignment(JTextField.CENTER);
        greenTeamHeading.setBackground(new Color(0,200,0));
        greenTeamHeading.setBorder(new LineBorder(Color.WHITE,2));
        panelPlayerEntry.add(greenTeamHeading);
        
        redTeamHeading.setBounds(frameWidth/2+width/2+horizontalSpacing/2,y-(height+40)-verticalSpacing,width,height+10);
        redTeamHeading.setEditable(false);
        redTeamHeading.setHorizontalAlignment(JTextField.CENTER);
        redTeamHeading.setBackground(new Color(200,0,0));
        redTeamHeading.setBorder(new LineBorder(Color.WHITE,2));
        panelPlayerEntry.add(redTeamHeading);

        
        //Set up Green team ID column
        greenIDHeading.setBounds(frameWidth/2-2*width-horizontalSpacing*3,startY-height-verticalSpacing,width,height);
        greenIDHeading.setEditable(false);
        greenIDHeading.setHorizontalAlignment(JTextField.CENTER);
        greenIDHeading.setBackground(null);
        greenIDHeading.setBorder(new LineBorder(Color.WHITE,0));
        greenIDHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(greenIDHeading);

        //Set up Green team Name column heading
        greenNameHeading.setBounds(frameWidth/2-width-horizontalSpacing*2,startY-height-verticalSpacing,width,height);
        greenNameHeading.setEditable(false);
        greenNameHeading.setHorizontalAlignment(JTextField.CENTER);
        greenNameHeading.setBackground(null);
        greenNameHeading.setBorder(new LineBorder(Color.WHITE,0));
        greenNameHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(greenNameHeading);

        //Set up Red team Name column heading  
        redNameHeading.setBounds(frameWidth/2+width+2*horizontalSpacing*2,startY-height-verticalSpacing,width,height);
        redNameHeading.setEditable(false);
        redNameHeading.setHorizontalAlignment(JTextField.CENTER);
        redNameHeading.setBackground(null);
        redNameHeading.setBorder(new LineBorder(Color.WHITE,0));
        redNameHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(redNameHeading);

        //Set up Red team ID column
        redIDHeading.setBounds(frameWidth/2+horizontalSpacing*2,startY-height-verticalSpacing,width,height);
        redIDHeading.setEditable(false);
        redIDHeading.setHorizontalAlignment(JTextField.CENTER);
        redIDHeading.setBackground(null);
        redIDHeading.setBorder(new LineBorder(Color.WHITE,0));
        redIDHeading.setForeground(new Color(10,20,75));
        panelPlayerEntry.add(redIDHeading);
        

        for(int i = 0; i<2; i++){

            //Setup the ID column
            for(int j = 0; j<15; j++)
            {
                playerIDFields[j+(15*i)] = new JTextField();
                playerIDFields[j+(15*i)].setBounds(x,y,width,height);
                playerIDFields[j+(15*i)].setBorder(new LineBorder(Color.BLACK,1));
                playerIDFields[j+(15*i)].setHorizontalAlignment(JTextField.CENTER);
                playerIDFields[j+(15*i)].setBackground(Color.WHITE);
                playerIDFields[j+(15*i)].setForeground(Color.BLACK);
                playerIDFields[j+(15*i)].addActionListener(listenerID);
                panelPlayerEntry.add(playerIDFields[j+(15*i)]);

                y+= height + verticalSpacing;
            }

            x += width + horizontalSpacing;
            y = startY;

            //Setup the name column
            for(int j = 0; j<15; j++)
            {
                playerNameFields[j+(15*i)] = new JTextField();
                playerNameFields[j+(15*i)].setBounds(x,y,width,height);
                playerNameFields[j+(15*i)].setBorder(new LineBorder(Color.BLACK,1));
                playerNameFields[j+(15*i)].setEditable(false);
                playerNameFields[j+(15*i)].setBackground(Color.WHITE);
                playerNameFields[j+(15*i)].setForeground(Color.BLACK);
                playerNameFields[j+(15*i)].addActionListener(listenerName);
                panelPlayerEntry.add(playerNameFields[j+(15*i)]);

                y+= height + verticalSpacing;
            }

            x += width + horizontalSpacing*4;
            y = startY;       
        }
    }

    public int searchFieldsArray(JTextField[] array, JTextField source)
    {
        int index = -1;

        for(int i = 0; i<array.length; i++)
        {
            if(array[i] == source)
            {
                index = i;
                break;
            }
        }

        return index;
    }
}