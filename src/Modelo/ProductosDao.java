
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;


public class ProductosDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Productos pro){
        String sql = "INSERT INTO productos_sdv (codigo, nombre, proveedor, stock, precio) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public void ConsultarProveedor (JComboBox proveedor){
        String sql = "SELECT nombre FROM proveedor_sdv";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
               proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public List ListarProductos(){
        List<Productos> Listapro = new ArrayList();
        String sql = "SELECT * FROM productos_sdv";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                Listapro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }
    
     public boolean EliminarProductos(int id){
        String sql = "DELETE FROM productos_sdv WHERE id =?";
        try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
      
    }
     
     public boolean ModificarProductos(Productos pro){
        String sql = "UPDATE productos_sdv SET codigo=?, nombre=?, proveedor=?, stock=?, precio=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.setInt(6, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
     
     public Productos BuscarPro (String cod){
         Productos producto = new Productos();
         String sql = "SELECT * FROM productos_sdv WHERE codigo = ?";
         try {
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
             ps.setString(1, cod);
             rs = ps.executeQuery();
             if (rs.next()) {
                 producto.setNombre(rs.getString("nombre"));
                 producto.setPrecio(rs.getDouble("precio"));
                 producto.setStock(rs.getInt("stock"));
                 
             }
   
         } catch (SQLException e) {  
             System.out.println(e.toString());
         }
         return producto;
         
     }
     
      public Config BuscarDatos (){
         Config conf = new Config();
         String sql = "SELECT * FROM config_sdv";
         try {
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
             if (rs.next()) {
                 conf.setId(rs.getInt("id"));
                 conf.setRuc(rs.getInt("ruc"));
                 conf.setNombre(rs.getString("nombre"));
                 conf.setTelefono(rs.getInt("telefono"));
                 conf.setDireccion(rs.getString("direccion"));
                 conf.setRazon(rs.getString("razon"));
                 
             }
   
         } catch (SQLException e) {  
             System.out.println(e.toString());
         }
         return conf;
         
     }
     
      
}


