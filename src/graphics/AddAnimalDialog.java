package graphics;

import animals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

/**
 * Animal class Animal type: cannibal, omnivorous, herbivore
 */
public class AddAnimalDialog extends JDialog  implements ActionListener {
    String[] canivorestring = {"Lion"};
    String[] omnivorestring = { "Bear"};
    String[] nebivorestring = {"Elephant", "Giraffe", "Turtle"};
    private JComboBox<String>box =new JComboBox<String>();
    private final JTextField size_field ;
    private final JTextField vertical_speed_field;
    private final JTextField horizon_speed_field;
    AbstractFactory f;
    public AddAnimalDialog(ArrayList<Animal> animals_list, String t, Observer ob, String f) {
        super(new Frame(), "Add animal", true);
        System.out.println("AddAnimalDialog constructor");
        JPanel displayPanel = new JPanel();
        JButton create = new JButton("Create");
        create.addActionListener(this);

        box = new JComboBox();
        JComboBox<String> omnibox = new JComboBox<String>(omnivorestring);
        JComboBox<String> hebibox = new JComboBox<String>(nebivorestring);
        JComboBox<String> carnibox = new JComboBox<String>(canivorestring);
        switch (f) {
            case "Carnivore" -> box = carnibox;
            case "Omnivore" -> box = omnibox;
            case "Herbivore" -> box = hebibox;
        }

        box.addActionListener(this);
        String[] colors = {"Natural", "Red", "Blue"};
        JComboBox<String>  animal_color = new JComboBox(colors);
        animal_color.addActionListener(this);
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        ImageIcon warning = new ImageIcon("images\\warning.png");
        JLabel label = new JLabel();
        label = new JLabel("Select animal");
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);
        displayPanel.add(label);
        displayPanel.add(box);
        String placeholder = "150";
        displayPanel.add(new JLabel("Size: (50 - 300)"));
        displayPanel.add(size_field = new JTextField(placeholder, 10));
        placeholder = "5";
        displayPanel.add(new JLabel("Vertical speed: (1 - 10)"));
        displayPanel.add(vertical_speed_field = new JTextField(placeholder, 10));
        displayPanel.add(new JLabel("Horizon speed: (1 - 10)"));
        displayPanel.add(horizon_speed_field = new JTextField(placeholder, 10));
        displayPanel.add(animal_color);
        this.setBounds(200, 150, 300, 300);
        create.addActionListener(e -> {
            try {
                String animal_chosen = (String) box.getItemAt(box.getSelectedIndex());
                int size = Integer.parseInt(size_field.getText());
                int verSpeed = Integer.parseInt(vertical_speed_field.getText());
                int horSpeed = Integer.parseInt(horizon_speed_field.getText());
                String color = animal_color.getItemAt(animal_color.getSelectedIndex());
                String name = "default";
                float weight;
                if (50 > size || size > 300 || horSpeed < 1 || horSpeed > 10
                        || verSpeed < 1 || verSpeed > 10)
                    throw new NumberFormatException();
                switch (animal_chosen) {
                    case "Lion" -> {
                        weight = (float) (size * 0.8);
                        Animal lion = gertFactory("Carnivore").getAnimal("Lion", "Lion", weight ,  color, ZooPanel.getInstance());
                        lion.setVerSpeed(verSpeed);
                        lion.setHorSpeed(horSpeed);
                        lion.setSize(size);
                        lion.registerObserver(ob);
                        animals_list.add(lion);

                    }
                    case "Bear" -> {
                        weight = (float) (size * 1.5);
                        Animal bear = gertFactory("Omnivore").getAnimal("Bear", "Bear", weight ,  color, ZooPanel.getInstance());
                        bear.setVerSpeed(verSpeed);
                        bear.setHorSpeed(horSpeed);
                        bear.setSize(size);
                        animals_list.add(bear);
                        bear.registerObserver(ob);
                    }
                    case "Giraffe" -> {
                        weight = (float) (size * 2.2);
                        Animal giraffe =  gertFactory("Herbivore").getAnimal("Giraffe", "Giraffe", weight ,  color, ZooPanel.getInstance());
                        giraffe.setVerSpeed(verSpeed);
                        giraffe.setHorSpeed(horSpeed);
                        giraffe.setSize(size);
                        animals_list.add(giraffe);
                        giraffe.registerObserver(ob);
                    }
                    case "Elephant" -> {
                        weight = size * 10;
                        Animal elephant = gertFactory("Herbivore").getAnimal("Elephant", "Elephant", weight ,  color, ZooPanel.getInstance());
                        elephant.setVerSpeed(verSpeed);
                        elephant.setHorSpeed(horSpeed);
                        elephant.setSize(size);
                        animals_list.add(elephant);
                        elephant.registerObserver(ob);
                    }
                    case "Turtle" -> {
                        weight = (float) (size * 0.5);
                        Animal turtle = gertFactory("Herbivore").getAnimal("Turtle", "Turtle", weight ,  color, ZooPanel.getInstance());
                        turtle.setHorSpeed(verSpeed);
                        turtle.setVerSpeed(horSpeed);
                        turtle.setSize(size);
                        animals_list.add(turtle);
                        turtle.registerObserver(ob);
                    }
                }
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Wrong input entered",
                        "Message", JOptionPane.ERROR_MESSAGE, warning);
            }
            dispose();
        });
        displayPanel.add(create);
        this.add(displayPanel);
        this.setVisible(true);
    }
    public  AbstractFactory gertFactory(String factory) {
        if(factory.equalsIgnoreCase("Carnivore"))
        {return new Carnivorfactory();}
        if(factory.equalsIgnoreCase("Herbivore"))
            return new HerbivorFactory();
        if (factory.equalsIgnoreCase("Omnivore"))
            return new Omnivorfactory();
        else {return null;}
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}