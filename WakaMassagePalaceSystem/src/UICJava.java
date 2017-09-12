/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import java.text.DateFormat;


public class UICJava {
    
    //https://stackoverflow.com/questions/30235183/how-do-we-disable-editing-options-on-jdatechooser

    /**
     *
     * @param chooser
     */
    public static void dcSetReadOnly(com.toedter.calendar.JDateChooser chooser){
        com.toedter.calendar.JTextFieldDateEditor editor = (com.toedter.calendar.JTextFieldDateEditor) chooser.getDateEditor();
        editor.setEditable(false);
    }
    
    //https://stackoverflow.com/questions/21012751/get-jdatechooser-date-to-jlabel
    public static String dcGetDate(com.toedter.calendar.JDateChooser chooser){
        java.util.Date date = chooser.getDate();
        return DateFormat.getDateInstance().format(date);
    }
    
}
