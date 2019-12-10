package TableModels;

import java.util.*;
import Entities.Template;

import javax.swing.table.AbstractTableModel;

public class TemplateTableModel extends AbstractTableModel {

    private List<Template> templateList;
    private List<String> colNameList;

    public TemplateTableModel (List<String> colNameList, List<Template> list) {
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
        return 5;
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
        return list.get(columnIndex);
    }
}
