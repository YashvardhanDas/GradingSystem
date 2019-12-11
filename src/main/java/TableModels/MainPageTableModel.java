package TableModels;

import Entities.Grades;
import Entities.Student;
import com.sun.tools.javac.util.JCDiagnostic;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;

public class MainPageTableModel extends AbstractTableModel {
    private List<List<Object>> students;
    private List<String> columnNames;

    public MainPageTableModel(List<String> columnNames, List<List<Object>> students) {
        this.students = students;
        this.columnNames = columnNames;
        for (int i = 0; i < students.size(); i++) {
            updateTotalGrade(i);
        }
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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex != 0) {
            if (columnIndex == columnNames.size() - 1) {
                students.get(rowIndex).set(columnIndex, aValue);
            } else {
                if (!isNumeric((String) aValue)) {
                    JOptionPane.showMessageDialog(null,
                            "Input should be numbers!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String colName = columnNames.get(columnIndex);
                    if (!(colName.equals("Student Name") || colName.equals("Total"))) {
                        double doubleValue = Double.valueOf((String) aValue);
                        ((Grades) students.get(rowIndex).get(columnIndex)).setGrade(doubleValue);
                        ((Grades) students.get(rowIndex).get(columnIndex)).setGraded(true);
                        updateTotalGrade(rowIndex);
                    }
                }
            }
        }
    }

    private void updateTotalGrade(int rowIndex) {
        List<Object> items = students.get(rowIndex);
        double sum = 0;
        for (Object o : items) {
            if (o instanceof Grades) {
                double categoryPercent = ((Grades) o).getAssignment().getCategoryPercent().getPercent();
                double assignmentPercent = ((Grades) o).getAssignment().getPercent();
                sum += ((Grades) o).getGrade() * categoryPercent * assignmentPercent;
            }
        }
        students.get(rowIndex).set(columnNames.size() - 2, sum);
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        String colName = columnNames.get(columnIndex);
        return !(colName.equals("Student Name") || colName.equals("Total"));
    }

    private boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
