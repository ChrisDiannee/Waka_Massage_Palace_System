/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author MichaelAngello
 */
public class manage_customer extends javax.swing.JFrame {

    /**
     * Creates new form crud
     */
    public manage_customer() {
        initComponents();
        
//         if(SQLite.openDB()){
//            String[] column = {"ID","firstName","lastName","gender"};
//            String[][] data = SQLite.readOnly("Customer");
//            
//            
//            
//            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, column){
//                @Override
//                public boolean isCellEditable(int row, int column) {
//                   //all cells false
//                   return false;
//                }
//            };
//            this.tblCustomers.setModel(model);
//            SQLite.closeDB(); 
//        
//        }
        this.refreshCustomersTable();
        this.refreshServicesTable();
        this.refreshTherapistTable();
        this.refreshTransactionsTable();
    }
    
    
    //    pag e click ang table mag gawas ang value sa text field automaticaly
    public void refreshCustomersTable(){
        //Load data
        if(SQLite.openDB()){
            String[] columns = {"ID", "firstName", "lastName", "gender"};
            String[][] records = SQLite.read("Customer",columns);
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(records, columns);
            this.tblCustomers.setModel(model);
            
            //Additional COde Snippets for JTABLE behavior
            ListSelectionListener lsl = new ListSelectionListener(){
                public void valueChanged(javax.swing.event.ListSelectionEvent event) {
                    if (tblCustomers.getSelectedRow() > -1) {
                        int row = tblCustomers.getSelectedRow();
                        //JOptionPane.showMessageDialog(null, "Row is " + row);
                        if(SQLite.openDB()){
                            String sr_id = tblCustomers.getValueAt(tblCustomers.getSelectedRow(), 0).toString();
                            int id = Integer.parseInt(sr_id);
                            String[][] result = SQLite.read("Customer", "ID=" + id);
                            txtFName.setText(result[0][1]);
                            txtLName.setText(result[0][2]);
//                            txtGender.setText(result[0][3]);
                            txtGender.setSelectedItem(result[0][3]);
                          
                            
                            SQLite.closeDB();
                        }
                    }
                }
            };
            this.tblCustomers.getSelectionModel().addListSelectionListener(lsl);
            
            SQLite.closeDB();
        }        
    }
    
    
     //    pag e click ang table mag gawas ang value sa text field automaticaly
    public void refreshServicesTable(){
        //Load data
        if(SQLite.openDB()){
            String[] columns = {"ID", "service_name", "price", "allotted_time"};
            String[][] records = SQLite.read("Service",columns);
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(records, columns);
            this.tblServices.setModel(model);
            
            //Additional COde Snippets for JTABLE behavior
            ListSelectionListener lsl = new ListSelectionListener(){
                public void valueChanged(javax.swing.event.ListSelectionEvent event) {
                    if (tblServices.getSelectedRow() > -1) {
                        int row = tblServices.getSelectedRow();
                        //JOptionPane.showMessageDialog(null, "Row is " + row);
                        if(SQLite.openDB()){
                            String sr_id = tblServices.getValueAt(tblServices.getSelectedRow(), 0).toString();
                            int id = Integer.parseInt(sr_id);
                            String[][] result = SQLite.read("Service", "ID=" + id);
                            txtService.setText(result[0][1]);
                            txtPrice.setText(result[0][2]);
                            
                          
                            
                            SQLite.closeDB();
                        }
                    }
                }
            };
            this.tblServices.getSelectionModel().addListSelectionListener(lsl);
            
            SQLite.closeDB();
        }        
    }
    
    public void refreshTherapistTable(){
        //Load data
        if(SQLite.openDB()){
            String[] columns = {"ID", "first_name", "last_name", "gender","commission"};
            String[][] records = SQLite.read("Therapist",columns);
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(records, columns);
            this.tblTherapist.setModel(model);
            
            //Additional COde Snippets for JTABLE behavior
            ListSelectionListener lsl = new ListSelectionListener(){
                public void valueChanged(javax.swing.event.ListSelectionEvent event) {
                    if (tblTherapist.getSelectedRow() > -1) {
                        int row = tblTherapist.getSelectedRow();
                        //JOptionPane.showMessageDialog(null, "Row is " + row);
                        if(SQLite.openDB()){
                            String sr_id = tblTherapist.getValueAt(tblTherapist.getSelectedRow(), 0).toString();
                            int id = Integer.parseInt(sr_id);
                            String[][] result = SQLite.read("Therapist", "ID=" + id);
                            txtTherapist.setText(result[0][1] +" " +result[0][2]);
                           
                            
                          
                            
                            SQLite.closeDB();
                        }
                    }
                }
            };
            this.tblTherapist.getSelectionModel().addListSelectionListener(lsl);
            
            SQLite.closeDB();
        }        
    }

    
    
    public void refreshTransactionsTable(){
        //Load data
        if(SQLite.openDB()){
            
            String[] columns = {"ID", "date","room_name"};
            String[][] records = SQLite.read("Transactions",columns);
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(records, columns);
            this.tblTransactions.setModel(model);
            
            //Additional COde Snippets for JTABLE behavior
            ListSelectionListener lsl = new ListSelectionListener(){
                @Override
                public void valueChanged(javax.swing.event.ListSelectionEvent event) {
                    if (tblTransactions.getSelectedRow() > -1) {
                        int row = tblTransactions.getSelectedRow();
                        //JOptionPane.showMessageDialog(null, "Row is " + row);
                        if(SQLite.openDB()){
                            String sr_id = tblTransactions.getValueAt(tblTransactions.getSelectedRow(), 0).toString();
                            int id = Integer.parseInt(sr_id);
                            String[][] result = SQLite.read("Transactions", "ID=" + id);
                            //date
                            txtRoom.setText(result[0][2]);
                            
                            String date = result[0][1];
                            
                            try {
                                java.util.Date date2 = new SimpleDateFormat("MMM dd, yyy").parse(date);
                                DateChooser.setDate(date2);
                            } catch (ParseException ex) {
                                Logger.getLogger(manage_customer.class.getName()).log(Level.SEVERE, null, ex);  
                            }
                            //timestarted
//                            time_start.setText(result[0][6]);

                            
                            
                          
                            
                            SQLite.closeDB();
                        }
                    }
                }
            };
            this.tblTransactions.getSelectionModel().addListSelectionListener(lsl);
            
            SQLite.closeDB();
        }        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRoom = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        DateChooser = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtPrice = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        time_start = new lu.tudor.santec.jtimechooser.JTimeChooser();
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtService = new javax.swing.JTextField();
        txtRoom = new javax.swing.JTextField();
        txtTherapist = new javax.swing.JTextField();
        txtLName = new javax.swing.JTextField();
        txtGender = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTransactions = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblServices = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTherapist = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1"},
                {"2"},
                {"3"},
                {"4"},
                {"5"},
                {"6"},
                {"COUPLE"}
            },
            new String [] {
                "Room Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRoom.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblRoom);
        if (tblRoom.getColumnModel().getColumnCount() > 0) {
            tblRoom.getColumnModel().getColumn(0).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, 170, 180));

        jLabel7.setBackground(new java.awt.Color(204, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setText("LIST OF TRANSACTIONS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 450, 54));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Service Selected:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("DATE: ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("Therapist:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setText("Room #:");

        jButton2.setText("UPDATE");

        jButton3.setText("ADD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DELETE");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setText("Total Payment: ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Customer's First Name:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Last Name:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Gender:");

        time_start.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setText("Time-Start:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("P");

        txtService.setEditable(false);

        txtTherapist.setEditable(false);

        txtGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel20)
                            .addComponent(jLabel18)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtService, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(txtRoom)
                            .addComponent(txtTherapist)
                            .addComponent(time_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1))
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(79, 79, 79)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel12)
                                .addComponent(jLabel22))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFName, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addComponent(txtLName)
                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtLName))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtService, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(txtTherapist, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel21))
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 560, 560));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("Manage Customers");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 360, 40));

        tblTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "TRANSACTION #", "DATE", "ROOM NAME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTransactions.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblTransactions);
        if (tblTransactions.getColumnModel().getColumnCount() > 0) {
            tblTransactions.getColumnModel().getColumn(0).setResizable(false);
            tblTransactions.getColumnModel().getColumn(1).setResizable(false);
            tblTransactions.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 720, 1720, 220));

        jScrollPane5.setAlignmentX(0.9F);

        tblCustomers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Customer ID", "Last Name", "First Name", "Gender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomers.setAlignmentX(3.0F);
        tblCustomers.setAlignmentY(3.0F);
        tblCustomers.setName(""); // NOI18N
        tblCustomers.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblCustomers);
        if (tblCustomers.getColumnModel().getColumnCount() > 0) {
            tblCustomers.getColumnModel().getColumn(0).setResizable(false);
            tblCustomers.getColumnModel().getColumn(1).setResizable(false);
            tblCustomers.getColumnModel().getColumn(2).setResizable(false);
            tblCustomers.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 570, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("SERVICES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("CUSTOMERS");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, -1, -1));

        jTextField1.setText("search");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 80, 210, -1));

        jTextField2.setText("search");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 200, -1));

        tblServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Service ID", "Name", "PRICE", "Allotted Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServices.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblServices);
        if (tblServices.getColumnModel().getColumnCount() > 0) {
            tblServices.getColumnModel().getColumn(0).setResizable(false);
            tblServices.getColumnModel().getColumn(1).setResizable(false);
            tblServices.getColumnModel().getColumn(2).setResizable(false);
            tblServices.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 120, 570, 270));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("THERAPIST");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 420, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("ROOMS");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 420, -1, -1));

        tblTherapist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Therapist #", "First Name", "Last Name", "Gender", "Commission"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTherapist.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblTherapist);
        if (tblTherapist.getColumnModel().getColumnCount() > 0) {
            tblTherapist.getColumnModel().getColumn(0).setResizable(false);
            tblTherapist.getColumnModel().getColumn(1).setResizable(false);
            tblTherapist.getColumnModel().getColumn(2).setResizable(false);
            tblTherapist.getColumnModel().getColumn(3).setResizable(false);
            tblTherapist.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 460, 960, 180));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_2.jpg"))); // NOI18N
        jLabel3.setAlignmentX(0.5F);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -270, 2140, 1260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
//        mag add ug customer tapos mabutang sa table ang info sa customer
        String firstname = this.txtFName.getText();
        String lastname = this.txtLName.getText();

       // String gender2 =this.txtGender.getText();
         String gender =this.txtGender.getSelectedItem().toString();
        String therapist =this.txtTherapist.getText();
        String room = this.txtRoom.getText();
        String service = this.txtService.getText();
        String price = this.txtPrice.getText();
       
        
        String date = UICJava.dcGetDate(DateChooser);
                  

        SQLite.openDB();
        int autoincrement = SQLite.autoincrement("Customer")+1;
        int autoincrement2 = SQLite.autoincrement("Transactions")+1;
            
            String sql = autoincrement+","+"'"+firstname+"'"+","+"'"+lastname+"'"+","+"'"+gender+"'";
            
            String tran = autoincrement2+","+"'"+date+"'"+","+"'"+room+"'";
            
            System.out.println("QUERY IS " + tran);
            {
            if(SQLite.create("Customer", sql)){
                JOptionPane.showMessageDialog(null, "You have successfully created an account!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Account cannot be added!");
            }
            }
//            
//            {
//            if(SQLite.create("Transactions", tran)){
//                JOptionPane.showMessageDialog(null, "You have successfully created an account!");
//            }
//            else{
//                JOptionPane.showMessageDialog(null, "Account cannot be added!");
//            }
//            }
            
            String table = "Transactions";
            String columns = autoincrement2+","+"'"+date+"'"+","+"'"+room+"'";
//                String stmts = "?,?,?";
//                String[] values = {autoincrement2, date, room};
                
                if(SQLite.create(table, columns)){
                    JOptionPane.showMessageDialog(null, "Date inserted succesfully!");
                }else{
                    JOptionPane.showMessageDialog(null, "Unable to save date picked!");
                }
                
                
                
                
                
                
                
            
            SQLite.closeDB();
            
//            pag click sa add mag refresh ang customers table then makita na ang bagong gi add.
            this.refreshCustomersTable();
            this.refreshTransactionsTable();
        
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(manage_customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manage_customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manage_customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manage_customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manage_customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblCustomers;
    private javax.swing.JTable tblRoom;
    private javax.swing.JTable tblServices;
    private javax.swing.JTable tblTherapist;
    private javax.swing.JTable tblTransactions;
    private lu.tudor.santec.jtimechooser.JTimeChooser time_start;
    private javax.swing.JTextField txtFName;
    private javax.swing.JComboBox<String> txtGender;
    private javax.swing.JTextField txtLName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRoom;
    private javax.swing.JTextField txtService;
    private javax.swing.JTextField txtTherapist;
    // End of variables declaration//GEN-END:variables
}
