
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.management.relation.RoleResult;

public class VentaDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int IdVenta(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM ventas_sdv";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
    
   public int RegistrarVenta(Venta v){
       String sql = "INSERT INTO ventas_sdv (cliente, vendedor, total, fecha) VALUES (?,?,?,?)";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, v.getCliente());
           ps.setString(2, v.getVendedor());
           ps.setDouble(3, v.getTotal());
           ps.setString(4, v.getFecha());
           ps.execute();
       } catch (SQLException e) {
           System.out.println(e.toString());
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
       return r;
   }
   
   public int RegistrarDetalle(Detalle Dv){
       String sql = "INSERT INTO detalle_sdv(cod_pro, cantidad, precio, id_venta) VALUES (?,?,?,?)";
       try {
           con  = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, Dv.getCod_pro());
           ps.setInt(2, Dv.getCantidad());
           ps.setDouble(3, Dv.getPrecio());
           ps.setInt(4, Dv.getId());
           ps.execute();
       } catch (SQLException e) {
           System.out.println(e.toString());
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
       return r;
       
   }
   
   public boolean ActualizarStock(int cant, String cod){
      String sql = "UPDATE productos_sdv SET stock = ? WHERE codigo = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, cant);
           ps.setString(2, cod);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
   } 
   
}
