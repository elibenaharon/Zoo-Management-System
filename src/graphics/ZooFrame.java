package graphics;

import animals.Animal;

import javax.swing.*;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class ZooFrame extends JFrame {

    /**
     * constructor
     */
    public ZooFrame() {
        System.out.println("Frame constractor ");
        ZooPanel.getInstance();
        ImageIcon icon = new ImageIcon("images\\Logo.png");
        ImageIcon warning = new ImageIcon("images\\warning.png");
        this.setTitle("Zoo Simulation 2.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 680);
        this.setIconImage(icon.getImage());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu BackgroundMenu = new JMenu("Background");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(BackgroundMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
        this.add(ZooPanel.getInstance());
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem imageItem = new JMenuItem("Image");
        JMenuItem greenItem = new JMenuItem("Green");
        JMenuItem noneItem = new JMenuItem("None");
        JMenuItem helpItem = new JMenuItem("Help");
        fileMenu.add(exitItem);
        BackgroundMenu.add(imageItem);
        BackgroundMenu.add(greenItem);
        BackgroundMenu.add(noneItem);
        helpMenu.add(helpItem);
        exitItem.addActionListener(e -> {System.exit(0);
            Animal.goodbye();});
        imageItem.addActionListener(e -> ZooPanel.getInstance().setBackground("savanna"));
        greenItem.addActionListener(e -> ZooPanel.getInstance().setBackground("green"));
        noneItem.addActionListener(e -> ZooPanel.getInstance().setBackground("none"));
        helpItem.addActionListener(e -> JOptionPane.showMessageDialog(null,"Home Work 4\nSend help PLS!!:(", "Message", JOptionPane.ERROR_MESSAGE,warning));
    }
    /**
     * Central Function
     * @param args
     */
    public static void main(String[] args) {
        ZooFrame f = new ZooFrame();
        f.setVisible(true);
        f.setResizable(false);
    }
}
