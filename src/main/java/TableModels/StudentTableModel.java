package TableModels;

import DatabaseManager.DatabaseManager;
import Entities.Grades;
import Entities.Student;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private String[] columnNames={"First Name","Last Name","BU ID","Email", "Is Freeze", "Comments"};
    private List<Student> students;
    private DatabaseManager databaseManager = new DatabaseManager();

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

    public String getColumnName(int col) {
        return this.columnNames[col];
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
            case 4:
                if (this.students.get(rowIndex).isFreezed()) {
                    result = "True";
                } else {
                    result = "False";
                }
                break;
            case 5:
                result = this.students.get(rowIndex).getComment();
                break;
        }
        return result;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                students.get(rowIndex).setName((String) aValue);
                break;
            case 1:
                students.get(rowIndex).setSurname((String) aValue);
                break;
            case 2:
                students.get(rowIndex).setBuId((String) aValue);
                break;
            case 3:
                students.get(rowIndex).setEmail((String) aValue);
                break;
            case 5:
                students.get(rowIndex).setComment((String) aValue);
                break;
        }
        updateStudent(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 4;
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }

    public void deleteRow(int rowIndex) {
        students.remove(rowIndex);
        fireTableDataChanged();
        databaseManager.remove(students.get(rowIndex));
    }

    public void changeFreeze(int rowIndex) {
        if (students.get(rowIndex).isFreezed()) {
            students.get(rowIndex).setFreezed(false);
        } else {
            students.get(rowIndex).setFreezed(true);
        }
        updateStudent(rowIndex);
    }

    public boolean getFreeze(int rowIndex) {
        return students.get(rowIndex).isFreezed();
    }

    private void updateStudent(int rowIndex) {
        databaseManager.update(students.get(rowIndex));
    }
}
