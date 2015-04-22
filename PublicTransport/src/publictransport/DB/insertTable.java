package publictransport.DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class insertTable {

    public insertTable() {
    }
    
    
    public void createTable()
    {
       Connection conn = null;
       Statement stmt = null;
       try{
       conn = DBConector.GetConnection();
      
      //STEP 4: Execute a query
      System.out.println("Creating table in given database...");
      stmt = conn.createStatement();
      
      String sql = "CREATE TABLE Cliente " +
                   "(ci INTEGER not NULL, " +
                   " nombre VARCHAR(25), " + 
                   " apellido VARCHAR(25), " + 
                   " celular VARCHAR(10), " +
                   " fechaNacimiento DATE, " +
                   " tipoUsuario VARCHAR(15), " +
                   " costoPasaje INTEGER, " +
                   " saldoDisponible INTEGER, " +
                   " PRIMARY KEY ( ci ))"; 

      stmt.executeUpdate(sql);
      System.out.println("Created table in given database...");
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      
   }
}
    
    public static void main(String[] args) {
        
        insertTable seed = new insertTable();
        seed.createTable();
         System.out.println("Goodbye!");
    }//end main
}//end JDBCExample
