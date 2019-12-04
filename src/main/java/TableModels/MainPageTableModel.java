package TableModels;

import Entities.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MainPageTableModel extends AbstractTableModel {
    private List<Student> students;


    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return students.get(0).getGrades().size()+1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        if(columnIndex==0){
            result=students.get(rowIndex);
        }else{
            result=students.get(rowIndex).getGrades().get(columnIndex);
        }

        return result;
    }
}
