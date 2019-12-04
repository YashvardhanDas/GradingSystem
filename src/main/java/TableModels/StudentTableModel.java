package TableModels;

import Entities.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private String[] columnNames={"name","surname","buId","Email"};
    private List<Student> students;

    public StudentTableModel(List<Student> students){
        this.students=students;
    }

    @Override
    public int getRowCount() {
        return this.students.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        switch (columnIndex){
            case 0:
                result = this.students.get(rowIndex).getName();
                break;
            case 1:
                result = this.students.get(rowIndex).getSurname();
                break;
            case 2:
                result = this.students.get(rowIndex).getBuId();
                break;
            case 3:
                result = this.students.get(rowIndex).getEmail();
                break;
        }
        return result;
    }
}
