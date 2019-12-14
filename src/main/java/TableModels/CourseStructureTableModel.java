package TableModels;

import java.util.*;

import DatabaseManager.DatabaseManager;
import Entities.Assignment;
import Entities.Template;

import javax.swing.table.AbstractTableModel;
import javax.xml.crypto.Data;

public class CourseStructureTableModel extends AbstractTableModel {
    private List<Assignment> assignmentList;
    private List<String> colNameList;


    public CourseStructureTableModel(List<String> colNameList, List<Assignment> list) {
        this.colNameList = colNameList;
        assignmentList = list;
    }

    @Override
    public int getRowCount() {
        return assignmentList.size();
    }

    @Override
    public int getColumnCount() {
        return colNameList.size();
    }

    @Override
    public String getColumnName(int col) {

        return colNameList.get(col);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Assignment assignment =  assignmentList.get(rowIndex);
        if (columnIndex == 0)
            return assignment.getName();
        else if(columnIndex==1){
            return assignment.getPercent();
        }else{
            return assignment.getTotalScore();
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        String value = (String) aValue;
        Assignment assignment = assignmentList.get(rowIndex);
        //name of assignment
        if (columnIndex == 0) {
            assignment.setName(value);
        }
        //percentage
        else if (columnIndex == 1){
            assignment.setPercent(Double.parseDouble(value));
        } else {
            assignment.setTotalScore(Double.parseDouble(value));
        }

        updateDatabase(assignment);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    private void updateDatabase(Assignment assignment) {
        DatabaseManager db = new DatabaseManager();
        db.update(assignment);
    }
}
