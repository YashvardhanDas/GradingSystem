package TableModels;

import java.util.*;
import Entities.Template;

import javax.swing.table.AbstractTableModel;

public class TemplateTableModel extends AbstractTableModel {

    private List<Template> templateList;
    private List<String> colNameList;

    public TemplateTableModel(List<String> colNameList, List<Template> list) {
        this.colNameList = colNameList;
        templateList = list;
    }

    @Override
    public int getRowCount() {
        return templateList.size();
    }

    @Override
    public int getColumnCount() {
        //TODO: get comlumn count from template class
        return colNameList.size();
    }

    @Override
    public String getColumnName(int col) {

        return colNameList.get(col);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Template template = templateList.get(rowIndex);
        List<String> list = new ArrayList<>();
        list.add(template.getName());
        list.add(template.getCategories());
        list.add(template.getCatPercent());
        list.add(template.getAssignNum());
        list.add(template.getAssignPercent());
        list.add(template.getAssignTotalScore());
        return list.get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String value = (String) aValue;
        Template template = templateList.get(rowIndex);

        if (columnIndex == 0) {
            template.setName(value);
        } else if (columnIndex == 1) {
            template.setCategories(value);
        } else if (columnIndex == 2) {
            template.setCatPercent(value);
        } else if (columnIndex == 3) {
            template.setAssignNum(value);
        } else if (columnIndex == 4) {
            template.setAssignPercent(value);
        } else if (columnIndex == 5) {
            template.setAssignTotalScore(value);
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}