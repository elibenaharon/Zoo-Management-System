package graphics;

import animals.Animal;
import diet.Carnivore;
import diet.Omnivore;
import plants.Plant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static graphics.IDrawable.PICTURE_PATH;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853,,,,,
 * @Vladislav Kelar 320467335
 */
public class ZooPanel extends JPanel  {
    private  static volatile ZooPanel  zooPanel = null;
    private BufferedImage image = null;
    private  ArrayList<Animal> animals_list;
    private  Plant plant;
    private boolean singelplant = false;
    private Boolean Check = false;
    private final ImageIcon icon;
    private static final int LETTUCE = 1;
    private static final int CABBAGE = 2;
    private static final int MEAT = 3;
    private static final int DELETE = 4;
    private Executor e ;
    //private int corePoolSize = 10, maxPoolSize = 10, keepAliveTime = 5000;
    //private  static ExecutorService mypool = Executors.newFixedThreadPool(5);
    private Observer ob = new ObserverClass();
    private ZoopanelHistory panelH = new ZoopanelHistory(this);

    /**
     * constructor
     */
    private ZooPanel() {//privete constractor
        //Thread controller = new Thread( this);
        animals_list = new ArrayList<Animal>();
        plant = null;
        icon = new ImageIcon(PICTURE_PATH+"\\images\\Warning.png");
        this.setLayout(new BorderLayout());
        this.setSize(800, 680);
        JPanel LowerPanel = new JPanel();
        LowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        LowerPanel.setBackground(Color.CYAN);
        JButton add_animal_btn = new JButton("Add Animal");
        JButton sleep_btn = new JButton("Sleep");
        JButton wakeup_btn = new JButton("Wake Up");
        JButton clear_btn = new JButton("Clear");
        JButton food_btn = new JButton("Food");
        JButton info_btn = new JButton("Info");
        JButton Color_animal_btn = new JButton("Color");
        JButton Save_bth = new JButton("Save");
        JButton Restore_bth = new JButton("Restore");
        JButton exit_btn = new JButton("Exit");
        LowerPanel.add(add_animal_btn);
        LowerPanel.add(sleep_btn);
        LowerPanel.add(wakeup_btn);
        LowerPanel.add(clear_btn);
        LowerPanel.add(food_btn);
        LowerPanel.add(info_btn);
        LowerPanel.add(Color_animal_btn);
        LowerPanel.add(Save_bth);
        LowerPanel.add(Restore_bth);
        LowerPanel.add(exit_btn);
        this.add(LowerPanel, BorderLayout.SOUTH);
        add_animal_btn.addActionListener(e -> Openaddanimaldialog());
        Color_animal_btn.addActionListener( e -> new ChangeColor(ZooPanel.this, new ZooFrame()));
        sleep_btn.addActionListener(e -> {
            if (animals_list.size() == 0) {
                JOptionPane.showMessageDialog(null, "There are no animals that can be sent to sleep, Add an animal first!",
                        "Message", JOptionPane.ERROR_MESSAGE, icon);
            }
            else
                for (Animal A :animals_list)
                    if(!A.getThreadSuspended())
                        A.setSuspended();
        });

        wakeup_btn.addActionListener(e -> {
            if (animals_list.size() == 0) {
                JOptionPane.showMessageDialog(null, "There are no animals that can be waked up, Add an animal first!",
                        "Message", JOptionPane.ERROR_MESSAGE, icon);
            }
            else
                for (Animal A :animals_list)
                    if(A.getThreadSuspended())
                        A.setResumed();
        });

        clear_btn.addActionListener(e -> {
            for(Animal A:animals_list)
                    A.setStop(true);
            DeleteAllAnimals();
            setPlant(DELETE);
            this.repaint();
        });

        food_btn.addActionListener(e -> {
            JDialog foodDialog = new JDialog(new ZooFrame(), "Food for animals", true);
            JButton Lettuce = new JButton("Lettuce");
            JButton Cabbage = new JButton("Cabbage");
            JButton Meat = new JButton("Meat");
            Lettuce.addActionListener(e1 -> {
                ZooPanel.this.setPlant(LETTUCE);
                foodDialog.dispose();
                repaint();
            });

            Cabbage.addActionListener(e1 -> {
                ZooPanel.this.setPlant(CABBAGE);
                foodDialog.dispose();
                repaint();
            });

            Meat.addActionListener(e1 -> {
                ZooPanel.this.setPlant(MEAT);
                foodDialog.dispose();
                repaint();
            });

            JLabel foodLabel = new JLabel("Please choose food");
            foodLabel.setIcon(icon);
            foodLabel.setIconTextGap(25);
            JPanel panel = new JPanel();
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(Cabbage);
            buttonPanel.add(Lettuce);
            buttonPanel.add(Meat);
            panel.setLayout(new BorderLayout());
            panel.add(buttonPanel, BorderLayout.SOUTH);
            panel.add(foodLabel,BorderLayout.CENTER);
            foodDialog.add(panel);
            foodDialog.pack();
            foodDialog.setVisible(true);
            setVisible(true);
        });

        info_btn.addActionListener(e -> {
            JDialog info_dialog = new JDialog(new ZooFrame(), "Zoo info", true);
            InfoTable at = new InfoTable(ZooPanel.this);
            JTable table = new JTable(at);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setPreferredScrollableViewportSize(new Dimension(500, 100));
            info_dialog.add(new JScrollPane(table));
            info_dialog.pack();
            info_dialog.setVisible(true);
        });
        Save_bth.addActionListener(e->{this.panelH.hitSave(); });
        Restore_bth.addActionListener(e->{this.panelH.hitUndo();});

        exit_btn.addActionListener(e -> {
            for (Animal a:animals_list)
                a.setStop(true);
            Animal.goodbye();
            System.exit(0);
           });

    }

    private void Openaddanimaldialog()
    {
        Object[] foodTypes = {"Omnivore", "Carnivore", "Herbivore" };
        String factory = "";
        int n = JOptionPane.showOptionDialog(null, "Please choose animal type", "Choose animal type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, foodTypes, foodTypes[2]);
        switch (n) {
            case 0 -> { factory = "Omnivore"; }
            case 1 -> { factory = "Carnivore"; }
            case 2 -> { factory = "Herbivore"; }
            default -> { }
        }
        if(!factory.equals("")) {
            int MAX_ANIMAL_AMOUNT = 15;
            if(animals_list.size()< MAX_ANIMAL_AMOUNT) {
                AddAnimalDialog animalAdded = new AddAnimalDialog(animals_list,"Add an animal to the zoo: ",ob,factory);}
            else {JOptionPane.showMessageDialog(
                    null, "You cannot add more than "+ MAX_ANIMAL_AMOUNT +" animals");}
        }
    }
    /** Responses to click events
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(Check) {
            Dimension size = this.getSize();
            gr.drawImage(image, 0, 0, size.width, size.height, this);
        }
        try {
            for(Animal animals: animals_list)
                animals.drawObject(gr);
            plant.drawObject(gr);
        }
        catch(Exception ignored) { }//picking non stop
    }
    /**
     * set Background
     * @param Picture Background picture
     */
    public void setBackground(String Picture) {
        switch (Picture){
            case "savanna"-> {
                try {
                    image = ImageIO.read(new File(PICTURE_PATH + "\\images\\savanna.png"));
                    Check =true;
                    this.setBackground((Color) null);
                    repaint();
                }
                catch (IOException e) { e.printStackTrace(); }
            }
            case "green"-> {
                image = null;
                Check =false;
                this.setBackground(Color.GREEN);
                repaint();
            }
            default -> {
                image = null;
                Check =false;
                this.setBackground(Color.WHITE);
                repaint();
            }
        }
    }
    /**
     * Background picture Zoo panel
     */
    public void manegezoo( ) {
            repaint();
            List<Animal> toRemove = new ArrayList<>();
            for(Animal animal : animals_list) {
                if(animal.getDiet() instanceof Carnivore || animal.getDiet() instanceof Omnivore)
                    for (Animal value : animals_list)
                        if ((animal.calcDistance(value.getLocation()) < value.getWeight()) && (animal.getWeight() > 2 * value.getWeight()) && animal.eat(value)) {
                            toRemove.add(value);
                            animal.eatInc();
                            //value.setStop(true);
                            repaint();
                        }
                if(this.plant != null && Math.abs(plant.getLocation().getX() - animal.getLocation().getX()) < 10 && Math.abs(plant.getLocation().getY() - animal.getLocation().getY()) < 10 && animal.eat(this.plant)) {
                    this.plant = null;
                    animal.eatInc();
                    repaint();
                }
            }
            for(Animal A :toRemove)
                A.setStop(true);//////////////////////--------------------------------------------
            animals_list.removeAll(toRemove);
            repaint();
        }

    /**
     * @param animal The function adds an animal to the animal register
     */
    public void addAnimalList(Animal animal) {
        try {
            if (animals_list.size() == 10)
                throw new ArrayIndexOutOfBoundsException();
            animals_list.add(animal);
        }
        catch(ArrayIndexOutOfBoundsException error) {
            JOptionPane.showMessageDialog(null, "You cannot add more than 10 animals", "Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * set Plant
     * @param Planet Plant
     */
    public void setPlant(int Planet ) {
        if (!singelplant) {
            switch (Planet) {
                case 1 -> {
                    this.plant = Plant.getInstance(ZooPanel.getInstance(), "Lettuce");
                    this.plant.loadImages("lettuce.png");
                    singelplant = true;
                    plant.setSngelton(true);
                    repaint();
                }
                case 2 -> {
                    this.plant = Plant.getInstance(ZooPanel.getInstance(), "Cabbage");
                    System.out.println(plant);
                    this.plant.loadImages("cabbage.png");
                    singelplant = true;
                    plant.setSngelton(true);
                    repaint();
                }
                case 3 -> {
                    this.plant = Plant.getInstance(ZooPanel.getInstance(), "Meat");
                    this.plant.loadImages("meat.gif");
                    singelplant = true;
                    plant.setSngelton(true);
                    repaint();
                }
                default -> this.plant = null;
            }
            repaint();
        }
        repaint();
    }
    /**
     * @return Animal Size
     */
    public int getAnimalSize() { return this.animals_list.size(); }
    /**
     * @return Animals
     */
    public ArrayList<Animal> getAnimals() { return this.animals_list; }
    /**
     * @return Plant
     */
    public Plant getPlant() { return this.plant; }
    /**
     * Delete AllAnimals
     */
    public void DeleteAllAnimals() { animals_list.clear(); }

    /**
     * ZooPanel get Instance
     * @return Reference to an existing object
     */
    public static  ZooPanel getInstance() {
        if (zooPanel == null)
                synchronized (ZooPanel.class) {
                    if(zooPanel == null)
                        zooPanel = new ZooPanel();
                }
        return zooPanel;
    }
    private static class PanelState extends Memento {
        private final ArrayList<Animal> animal_List1;
        public PanelState(ArrayList<Animal> animal_List) {
            super();
            this.animal_List1 = animal_List;
        }
        public ArrayList<Animal> getAnimalArrayList() { return this.animal_List1; }
    }
    public Memento save() {
        return new PanelState(cloneAnimalList());
    }
    public void restore(Memento state) {
        Animal.goodbye();
        Animal.setMypool();
        this.animals_list = ((PanelState)state).getAnimalArrayList();
        for(Animal animal : this.animals_list)
            Animal.addtomypool(animal);
        repaint();
    }
    public void notifyEmptyHistory()
    {
        JOptionPane.showMessageDialog(null, "No history to restore");
    }
    public void notifyFullHistory()
    {
        JOptionPane.showMessageDialog(null, "Can't add more than 3 states to history");
    }
    private ArrayList<Animal> cloneAnimalList() {
        ArrayList<Animal> clonedList = new ArrayList<Animal>();
        for (Animal animal : this.animals_list) {
            try {
                Animal cc = (Animal)animal.clone();
                cc.setSuspended();
                clonedList.add(cc);
            }
            catch (CloneNotSupportedException ignored) { }
        }
        return clonedList;
    }
}

