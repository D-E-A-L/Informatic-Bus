package DateBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Tableo {
    
    String cedula;
    String nombre;;
    String apellido;
    String celular;
    String fecha;
    String usuario;
    String pasaje;
    String saldo;                                                                        
    
    DBConector conexion;
    
    public Tableo() {
        conexion = new DBConector();
    }
    
    private double costoPasaje(String Tusuario)
    {
        double resp = 2;
        switch(Tusuario)
        {
            case "Niño": resp = 1;
            break;
            case "Colegial": resp = 1.5;
            break;   
            case "Universitario": resp = 1.8;
            break;
            case "Adulto Mayor": resp = 1.5;
            break;
        }
        return resp;
    }
    
    /*
    1) coneccion a base de datos
        Connection conexion= DBConector.GetConnection() ;
    2)las consultas se insertan como strings
		String query = " insert into Cliente (cedula, nombre, apellido, celular, "
    3) lee la consulta y prepara para ejecutarla en la base de datos    
		PreparedStatement preparedStmt = conexion.prepareStatement(query);
    4) ejecuta la consulta 
        preparedStmt.execute();
    */
    
    public void insertCliente(String cedula, String nombre, String apellidos, 
            String celular, String fecNac, String TUsuario, double saldo) throws SQLException      
    {   
    
        double costo = costoPasaje(TUsuario);
	String query = " insert into Cliente(cedula, nombre, apellido, celular, "
                    + "fechaNacimiento, tipoUsuario, costoPasaje, saldoDisponible)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
       
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement(query);
        preparedStmt.setString (1, cedula);
        preparedStmt.setString (2, nombre);
        preparedStmt.setString (3, apellidos);
        preparedStmt.setString (4, celular);
        preparedStmt.setString (5, fecNac);
        preparedStmt.setString (6, TUsuario);
        preparedStmt.setDouble (7, costo);
        preparedStmt.setDouble (8, saldo);

        // execute the preparedstatement
        preparedStmt.execute();
    }
    
    public void updateCliente(String cedula, String nombre, String apellidos, 
            String celular, String fecNac, String TUsuario, double saldo) throws SQLException
    {
        double costo = costoPasaje(TUsuario);
        try {            
            String query = "update Cliente(nombre = ? , apellido = ? , celular = ? , "
                    + "fechaNacimiento = ? , tipoUsuario = ? , costoPasaje = ? , saldoDisponible = ? )"
                    + " where cedula = ? ";
            PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement(query);
            //preparedStmt.setString (1, cedula);
            preparedStmt.setString (2, nombre);
            preparedStmt.setString (3, apellidos);
            preparedStmt.setString (4, celular);
            preparedStmt.setString (5, fecNac);
            preparedStmt.setString (6, TUsuario);
            preparedStmt.setDouble (7, costo);
            preparedStmt.setString(8, String.valueOf(cedula));
            preparedStmt.execute();
            preparedStmt.close();            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void deleteCliente(String cedula){  
        try {         
            PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement("DELETE FROM cliente where cedula = ?");          
            preparedStmt.setString(1, cedula);                   
            preparedStmt.execute();
            preparedStmt.close();            
        }catch(SQLException e){
            System.out.println(e);
        }            
   }
    
    public void createTable()
    {
       Statement stmt = null;
       try{
      
      //STEP 4: Execute a query
        stmt=conexion.GetConnection().createStatement();
      
        String sql = "CREATE TABLE Cliente " +
                   "(cedula VARCHAR(25) not NULL, " +
                   " nombre VARCHAR(25), " + 
                   " apellido VARCHAR(25), " + 
                   " celular VARCHAR(10), " +
                   " fechaNacimiento VARCHAR(10), " +
                   " tipoUsuario VARCHAR(15), " +
                   " costoPasaje DOUBLE, " +
                   " saldoDisponible DOUBLE, " +
                   //" imagen LONGBLOB, " +
                   " PRIMARY KEY ( cedula ))"; 

      stmt.executeUpdate(sql);
      System.out.println("tabla creada...");
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
            conexion.GetConnection().close();
      }catch(SQLException se){
      }// do nothing
   }
}
    
    /*obtenemos todos los datos de la tabla*/
    public Object [][] getDatosTabla() throws SQLException{
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try{         
           PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement("SELECT count(1) as total FROM cliente ");
           ResultSet resultado = preparedStmt.executeQuery();
           resultado.next();
           registros = resultado.getInt("total");
           resultado.close();
        }catch(SQLException e){
           System.out.println(e);
        }
        
        Object[][] datos = new String[registros][8];
        //realizamos la consulta sql y llenamos los datos en "Object"  
        try{ 
            PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement("SELECT cedula, nombre, apellido, celular"
                        + "fechaNacimiento, tipoUsuario, costoPasaje, saldoDisponible)"
                        + " FROM cliente"
                        + " ORDER BY cedula ");
            ResultSet resultado = preparedStmt.executeQuery();
            int i=0;
            while(resultado.next()){
                this.cedula = resultado.getString("cedula");
                this.nombre = resultado.getString("nombre");
                this.apellido = resultado.getString("apellido");
                this.celular = resultado.getString("celular");
                this.fecha = resultado.getString("fecNac");
                this.usuario = resultado.getString("TUsuario");
                this.pasaje = resultado.getString("costoPasaje");
                this.saldo = resultado.getString("saldoDisponible");
                datos[i][0] = cedula;            
                datos[i][1] = nombre;            
                datos[i][2] = apellido;            
                datos[i][3] = celular;            
                datos[i][4] = fecha;
                datos[i][5] = usuario;            
                datos[i][6] = pasaje;            
                datos[i][7] = saldo;            
                i++;
            }
            resultado.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return datos;
    }   
    
    /*obtenermos un dato de toda la tabla*/
    public void getDatos(String buscarcedula) throws SQLException{

        try{   
            Statement Stmt = conexion.GetConnection().createStatement();
            ResultSet registro=Stmt.executeQuery("SELECT * FROM cliente where cedula = 'buscarcedula'");
            Stmt.getConnection();
                       
            while(registro.next()){ 
                this.cedula = registro.getString("cedula");
                this.nombre = registro.getString("nombre");
                this.apellido = registro.getString("apellido");
                this.celular = registro.getString("celular");
                this.fecha = registro.getString("fecNac");
                this.usuario = registro.getString("TUsuario");
                this.pasaje = registro.getString("costoPasaje");
                this.saldo = registro.getString("saldoDisponible");
            }
            System.out.println(this.nombre);
            
            registro.close();
        }catch(SQLException e){
            System.out.println(e);
    } 
}    
    
    public String getNombre(String buscarcedula){
        try {         
            PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement("SELECT nombre FROM cliente where cedula = ?");          
            preparedStmt.setString(1, buscarcedula); 
            ResultSet registro=preparedStmt.executeQuery();
            if(registro.next()){
                InputStream stream = registro.getBinaryStream(1);
                this.nombre = registro.getString("nombre");
                System.out.println(this.nombre);
            }
            preparedStmt.execute();
            preparedStmt.close();            
        }catch(SQLException e){
            System.out.println(e);
        }
        return this.nombre;
    }
    
    public String getApellido(String buscarcedula){
        try {         
            PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement("SELECT apellido FROM cliente where cedula = ?");          
            preparedStmt.setString(1, buscarcedula); 
            ResultSet registro=preparedStmt.executeQuery();
            if(registro.next()){
                InputStream stream = registro.getBinaryStream(1);
                this.apellido = registro.getString("apellido");
                System.out.println(this.apellido);
            }
            preparedStmt.execute();
            preparedStmt.close();            
        }catch(SQLException e){
            System.out.println(e);
        }
        return apellido;
    }
    
    public String getFecha(String buscarcedula){
        try {         
            PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement("SELECT fechaNacimiento FROM cliente where cedula = ?");          
            preparedStmt.setString(1, buscarcedula); 
            ResultSet registro=preparedStmt.executeQuery();
            if(registro.next()){
                InputStream stream = registro.getBinaryStream(1);
                this.fecha = registro.getString("fechaNacimiento");
                System.out.println(this.fecha);
            }
            preparedStmt.execute();
            preparedStmt.close();            
        }catch(SQLException e){
            System.out.println(e);
        }
        return fecha;
    }
    
    public String getTelefono(String buscarcedula){
        try {         
            PreparedStatement preparedStmt = conexion.GetConnection().prepareStatement("SELECT celular FROM cliente where cedula = ?");          
            preparedStmt.setString(1, buscarcedula); 
            ResultSet registro=preparedStmt.executeQuery();
            if(registro.next()){
                InputStream stream = registro.getBinaryStream(1);
                this.celular = registro.getString("celular");
                System.out.println(this.celular);
            }
            preparedStmt.execute();
            preparedStmt.close();            
        }catch(SQLException e){
            System.out.println(e);
        }
        return celular;
    }
    
    public static void main(String[] args) {
        Tableo seed = new Tableo();
        seed.createTable();
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample
