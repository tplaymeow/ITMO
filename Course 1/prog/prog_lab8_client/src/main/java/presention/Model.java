package presention;

import model.Coordinates;
import model.Flat;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Model implements TableModel {
    private ArrayList<Flat> flats;

    {
        flats = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            flats.add(new Flat(i, "Govno", new Coordinates(i, i)));
    }

    @Override
    public int getRowCount() {
        return flats.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "name";
            case 2:
                return "x_coord";
            case 3:
                return "y_coord";
            case 4:
                return "time";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return int.class;
            case 1:
                return String.class;
            case 2:
            case 3:
                return Double.class;
            case 4:
                return LocalDateTime.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        System.out.println(rowIndex);
        Flat flat = flats.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return flat.getId();
            case 1:
                return flat.getHouseName();
            case 2:
                return flat.getCoordinates().getX();
            case 3:
                return flat.getCoordinates().getY();
            case 4:
                return flat.getLocalDateTime();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
