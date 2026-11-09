package graphics;

import animals.Animal;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class InfoTable extends AbstractTableModel {

    private final ArrayList<Animal> animals;
    private final String[] columnNames = {"Name","Color","Weight","Hor","Ver","Eat"};
    /**
     * @param zoopanel A "ZooPanel" object
     */
    public InfoTable(ZooPanel zoopanel) {
        animals = new ArrayList<>();
        for(int i = 0; i< zoopanel.getAnimalSize(); i++)
            animals.add(zoopanel.getAnimals().get(i));
    }
    /**
     * @return The size of the animal
     */
    @Override
    public int getRowCount() {
        return animals.size();
    }
    /**
     * @return Number of columns
     */
    @Override
    public int getColumnCount() {
        return 6;
    }
    /**
     * Initializes the information table
     * @param column Column number
     * @return jtkui djf
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    /**
     * Initializes the information table
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal a = animals.get(rowIndex);
        switch(columnIndex) {
            case 0->{ if(Objects.equals(a.getName(), "default")) {return a.getAnimalName();}
                      else {return a.getName();}}
            case 1->{ if(Objects.equals(a.getName(), "default")) {return a.getColor();}
                      else {return " ";}}
            case 2->{ if(Objects.equals(a.getName(), "default")) {return a.getWeight();}
                      else {return " ";}}
            case 3->{ if(Objects.equals(a.getName(), "default")) {return a.getHorSpeed();}
                      else {return " ";}}
            case 4->{ if(Objects.equals(a.getName(), "default")) {return a.getVerSpeed();}
                      else {return " ";}}
            case 5->{ return a.getEatCount();}
            //case 6->{} //remove specific animal from the list
            default -> { return null; }
        }
    }
}
