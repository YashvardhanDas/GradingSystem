package TableModels;

import Entities.Student;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MainPageTableModel extends AbstractTableModel {
    private List<List<Object>> students;
    private List<String> columnNames;

    public MainPageTableModel(List<String> columnNames, List<List<Object>> students) {
        this.students = students;
        this.columnNames = columnNames;
    }


    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    public String getColumnName(int col) {
        return this.columnNames.get(col);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        Object result = null;
//        if(columnIndex==0){
//            result = students.get(rowIndex).get(columnIndex);
//        }else{
//            result = students.get(rowIndex).get(columnIndex);
//        }

        return students.get(rowIndex).get(columnIndex);
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }
}
