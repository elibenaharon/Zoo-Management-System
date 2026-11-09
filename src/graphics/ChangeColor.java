package graphics;

import animals.Animal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChangeColor extends JDialog implements ActionListener {
    private ArrayList<String> animalsname;

    public ChangeColor(ZooPanel zoopanel, ZooFrame Zoo) {
        super(Zoo, "Change Color", true);
        this.setBounds(200, 150, 300, 300);
        JPanel choosPanel = new JPanel();
        String[] colors = {"Natural", "Red", "Blue"};
        JComboBox<String> animal_color = new JComboBox(colors);
        animal_color.addActionListener(this);
        choosPanel.setLayout(new BoxLayout(choosPanel, BoxLayout.PAGE_AXIS));
        choosPanel.add(animal_color);
        ArrayList<Animal> animals = zoopanel.getAnimals();
        for (Animal A: animals)
            animalsname.add(A.getName());
        JComboBox<String> animals_name = new JComboBox((ComboBoxModel) animalsname);
        JLabel label = new JLabel();
        label = new JLabel("Select animal");
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);
        choosPanel.add(label);
        choosPanel.add(animals_name);
        this.add(choosPanel);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) { }
}
