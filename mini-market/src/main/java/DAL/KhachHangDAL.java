package main.java.DAL;
import main.java.DTO.KhachHangDTO;
import java.sql.*;
import java.util.Vector;
public class KhachHangDAL {
    private Connection con;
    public boolean openConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl="jdbc:sqlserver://localhost\\PD:1433;database=mini_market;encrypt=false;";
            String username="sa";
            String password="12345678";
            con = DriverManager.getConnection(dbUrl, username, password);
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public void closeConnection(){
        try{
            if(con!=null);
            con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public Vector<KhachHangDTO> getAllKhachHang(){
        Vector<KhachHangDTO> arr = new Vector<KhachHangDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from KhachHang";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    KhachHangDTO khachhang = new KhachHangDTO();
                    khachhang.setMaKH(rs.getString("MaKH"));
                    khachhang.setTenKH(rs.getString("TenKH"));
                    khachhang.setSdtKH(rs.getString("SdtKH"));
                    khachhang.setKHEmail(rs.getString("EmailKH"));
                    arr.add(khachhang);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally {
                closeConnection();
            }
        }
        return arr;
    }
    public boolean addKhachHang(KhachHangDTO kh){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Insert into KhachHang values(?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, kh.getMaKH());
                stmt.setString(2, kh.getTenKH());
                stmt.setString(3, kh.getSdtKH());
                stmt.setString(4, kh.getKHEmail());
                if(stmt.executeUpdate() >= 1)
                    result = true;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean hasMaKH(String MaKH){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "SELECT * FROM KhachHang WHERE MaKH= '" + MaKH + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                result = rs.next();
            }catch(SQLException ex){
                System.out.println("Hàm hasMaKH bị lỗi");
            }finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean deleteKhachHang(String makh){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "DELETE FROM KhachHang WHERE MaKH = '" + makh + "'";
                Statement stmt = con.createStatement();
                int rowCount = stmt.executeUpdate(sql);
                if(rowCount > 0)
                    result = true;
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return result;
    }

    public boolean updateKhachHang(KhachHangDTO kh,String MaKH){
        boolean result = false;
        if(openConnection()){
            try{
                System.out.println( MaKH);
                String sql = "UPDATE KhachHang SET MaKH = ?, TenKH = ?, SdtKH = ?, EmailKH = ? Where MaKH = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, kh.getMaKH());
                stmt.setString(2, kh.getTenKH());
                stmt.setString(3, kh.getSdtKH());
                stmt.setString(4, kh.getKHEmail());
                stmt.setString(5,MaKH);
                int rowCount = stmt.executeUpdate();
                System.out.println(rowCount);
                if(rowCount > 0)
                    result = true;
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println("Hàm updateKhachHang bị lỗi");
            }finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean searchMaKH(Vector<KhachHangDTO> arr, String MaKH){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "SELECT * FROM KhachHang WHERE MaKH= '"+ MaKH +"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    KhachHangDTO khachhang = new KhachHangDTO();
                    khachhang.setMaKH(rs.getString("MaKH"));
                    khachhang.setTenKH(rs.getString("TenKH"));
                    khachhang.setSdtKH(rs.getString("SdtKH"));
                    khachhang.setKHEmail(rs.getString("EmailKH"));
                    arr.add(khachhang);
                }
                if(arr.size() > 0)
                    result = true;
            }catch(SQLException ex){
                System.out.println(ex);
                System.out.println("Hàm searchMaKH bị lỗi");
            }finally {
                closeConnection();
            }
        }
        return result;
    }


}
