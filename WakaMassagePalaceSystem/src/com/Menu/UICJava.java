

package com.Menu;

import java.awt.Color;
import java.text.DateFormat;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

public class UICJava {
    //https://stackoverflow.com/questions/30235183/how-do-we-disable-editing-options-on-jdatechooser
    public static void dcSetReadOnly(com.toedter.calendar.JDateChooser chooser){
        com.toedter.calendar.JTextFieldDateEditor editor = (com.toedter.calendar.JTextFieldDateEditor) chooser.getDateEditor();
        editor.setEditable(false);
    }
    
    //https://stackoverflow.com/questions/21012751/get-jdatechooser-date-to-jlabel
    public static String dcGetDate(com.toedter.calendar.JDateChooser chooser){
        java.util.Date date = chooser.getDate();
        return DateFormat.getDateInstance().format(date);
    }
    public static void setTransparentBg(JButton btn){
        btn.setBackground(new Color(0,0,0,0));
    }
    
    
//    public static void refreshTherapistTable(JTable tblTherapist){
//        //Load data
//        if(SQLite.openDB()){
//            String[] columns = {"ID", "first_name", "last_name", "gender","commission"};
//            String[][] records = SQLite.read("Therapist",columns);
//            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(records, columns);
//            
////            com.Menu.manage_customer manage_customer = new manage_customer();
////        manage_customer.setVisible(true);
//            tblTherapist.setModel(model);
//            
//            //Additional COde Snippets for JTABLE behavior
//            ListSelectionListener lsl = new ListSelectionListener(){
//                public void valueChanged(javax.swing.event.ListSelectionEvent event) {
//                    if (tblTherapist.getSelectedRow() > -1) {
//                        int row = tblTherapist.getSelectedRow();
//                        //JOptionPane.showMessageDialog(null, "Row is " + row);
//                        if(SQLite.openDB()){
//                            String sr_id = tblTherapist.getValueAt(tblTherapist.getSelectedRow(), 0).toString();
//                            int id = Integer.parseInt(sr_id);
//                            String[][] result = SQLite.read("Therapist", "ID=" + id);
//                            manage_customer.txtTherapist.setText(result[0][1] +" " +result[0][2]);
//                           
//                            
//                          
//                            
//                            SQLite.closeDB();
//                        }
//                    }
//                }
//            };
//            tblTherapist.getSelectionModel().addListSelectionListener(lsl);
//            
//            SQLite.closeDB();
//        }        
//    }
}
