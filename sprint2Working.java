import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class sprint2Working {
    private static BufferedImage image;

    private static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(src, 0, 0, w, h, null);
        g.dispose();
        return img;
    }
    
    static boolean showCard1 = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Timer CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        JPanel cardPanel = new JPanel(new CardLayout());

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

        JPanel card2 = new JPanel();
        card2.add(new JLabel("card2"));

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Column 1");
        tableModel.addColumn("Column 2");
        for (int i = 0; i < 16; i++)
        {
            tableModel.addRow(new Object[]{"Row " + (i + 1) + ", Column 1", "Row " + (i + 1) + ", Column 2"});
        }

        
        JTable table1 = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);
        card2.add(scrollPane);

        cardPanel.add(card2, "card2");

        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "card1");


        Timer timer = new Timer(3000, new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cardLayout.show(cardPanel, "card2");
            }
        });
        timer.start();

        frame.add(cardPanel);
        frame.setVisible(true);
    }
}