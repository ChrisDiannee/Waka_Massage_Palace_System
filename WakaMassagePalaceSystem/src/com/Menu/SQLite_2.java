package com.Menu;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class SQLite_2 extends javax.swing.JFrame {

//    /** Creates new form SQLite */
//    static java.sql.Connection conn = null;
//    static java.sql.Statement stmt = null;
//    static File temp = new File("db.sqlite");
//    static String url = "jdbc:sqlite:"+temp.getAbsolutePath().replace("\\", "\\\\");
////    static String url = "jdbc:sqlite:C:\\Users\\Andrew\\Desktop\\OOPexam\\src\\com\\mexam\\loginregister\\db.sqlite";
//    
//    
    
        static java.sql.Connection conn  = null;
    static java.sql.Statement stmt = null;
    static String url = "jdbc:sqlite:C:\\Users\\Andrew\\Google Drive\\School files\\IT3SE\\OOP\\CRUD WITH JCALENDAR & IMAGE\\src\\com\\profile\\db.sqlite";
    static String error = "";

    public SQLite_2() {
        initComponents();
        
        
    }
 
    //Open DB Session Method
    public static boolean openDB(){
    boolean result = false;
    try{
        Class.forName("org.sqlite.JDBC");
        conn = java.sql.DriverManager.getConnection(url);

        System.out.println("OK! SQLite DB session connected.");
        result = true;
    }
    catch(Exception e){
        error = e.getMessage();
        System.out.println("Open DB Error:" + e.getMessage());
    } 
    return result;
    }
    
    
    //Close DB Session Method
    public static boolean closeDB(){
        boolean result = false;
        try{
            conn.close();

            System.out.println("OK! SQLite DB session closed.");
            result = true;
        }
        catch(Exception e){
            error = e.getMessage();
            System.out.println("Close DB Error: " + e.getMessage());
        }
        return result;
    } 
    
        //Create Record Method
    public static boolean create(String table, String values){
        boolean result = false;
        try{
            SQLite.stmt = conn.createStatement();
            String query = "INSERT INTO "+ table +" VALUES(" + values + ")";
            stmt.executeUpdate(query);
            //You can include exception handling here. (e.g. Duplicate Data, etc.)
            result = true;
        }
        catch(Exception e){
            System.out.println("Create Error: " + e.getMessage());
        }
        return result;
    }
    
    //Update Record Method
    public static boolean update(String table, String set, int id){
        boolean result = false;
        try{
            SQLite.stmt = conn.createStatement();
            String query = "UPDATE "+ table +" SET " + set + " WHERE id=" + id;
            stmt.executeUpdate(query);
            //You can include exception handling here. (e.g. Duplicate Data, etc.)
            result = true;
        }
        catch(Exception e){
            System.out.println("Create Error: " + e.getMessage());
        }
        return result;
    }
    
    
    //Delete Record Method
    public static boolean delete(String table, int id){
        boolean result = false;
        try{
            SQLite.stmt = conn.createStatement();
            String query = "DELETE FROM "+ table + " WHERE id=" + id;
            stmt.executeUpdate(query);
            result = true;
        }
        catch(Exception e){
            System.out.println("Create Error: " + e.getMessage());
        }
        return result;
    } 
    

        //Read Record Method
    public static int autoincrement(String table){
        String[][] records = null;
        int totalColumns = 0;
        int count = 0;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            count = rs.getInt(1);
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            totalColumns = rsmd.getColumnCount();
            
            System.out.println(totalColumns);
            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return count;
    }
    
    public static String[][] read(String table){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            int totalRows = rs.getInt(1);
            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();
            //System.out.println(totalColumns);
            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
       public static int checkusername(String table, String username){
        int count= 0;
        String[][] records = null;
        int totalColumns = 0;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table+" WHERE USERNAME ='"+username+"'");count = rs.getInt(1);
            int totalRows = rs.getInt(1);
            count = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            totalColumns = rsmd.getColumnCount();
            System.out.println(totalColumns);
            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return count;
    }
    public static int checkpassword(String table, String username){
        int count= 0;
        String[][] records = null;
        int totalColumns = 0;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table+" WHERE PASSWORD ='"+username+"'");count = rs.getInt(1);
            int totalRows = rs.getInt(1);
            count = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            totalColumns = rsmd.getColumnCount();
            System.out.println(totalColumns);
            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return count;
    }
    public static String[][] readOnly(String table){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT ID, firstName, lastName, birthday FROM " + table);
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table);
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    public static String[][] readID(String table, int ID){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" where id is "+ ID +" order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    
    public static String[][] readusernmae(String table, String Name){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" where usernmae like '%"+ Name +"%' order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    public static String[][] readfirstname(String table, String Name){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" where firstname like '%"+ Name +"%' order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    public static String[][] readlastname(String table, String Name){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" where lastname like '%"+ Name +"%' order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    public static String[][] read(String table, String[] columns){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table);
            int totalRows = rs.getInt(1);

            //Count total columns
            int totalColumns = columns.length;
            String cols = "";
            for(int i=0;i<totalColumns;i++){
                cols += columns[i];
                if((i+1)!=totalColumns)cols+=", ";
            }
            rs = stmt.executeQuery("SELECT "+ cols +" FROM " + table);

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }

public static String[][] read(String table, String where){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table + " WHERE " + where);
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE " + where);
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    } 


    public static String[][] readContactNO(String table, String cont){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" where CONTACTNO like '"+ cont +"%' order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    
    public static String[][] readEmail(String table, String email){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table );
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table+" where email like '%"+ email +"%' order by id");
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    
    
    
public static int count(String table){
        String[][] records = null;
        int totalRows= 0;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table);
            totalRows = rs.getInt(1);
            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table);
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(Exception e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return totalRows;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SQLite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SQLite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SQLite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SQLite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SQLite_2().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


