package main.java.DAL;

import main.java.DTO.NCCDTO;

import java.sql.*;
import java.util.Vector;
public class NCCDAL {
        private Connection con ;
        public boolean openConnection(){
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String dbUrl = "jdbc:sqlserver://localhost\\PD:1433;database=mini_market;encrypt=false;";
                String username ="sa"; String password = "12345678";
                con = DriverManager.getConnection(dbUrl,username,password);
                return true;
            }catch (Exception e){
                    System.out.println(e);
                    return false;
            }
        }
    public void closeConnection() {
        try {
            if (con != null)
                con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public Vector<NCCDTO> getAllNCC(){
        Vector<NCCDTO> arr = new Vector<NCCDTO>();
        if(openConnection()){
            try {
                String sql = "SELECT * FROM NCC";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    NCCDTO ncc = new NCCDTO();
                    ncc.setMaNCC(rs.getString("MaNCC"));
                    ncc.setTenNCC(rs.getString("TenNCC"));
                    ncc.setSdtNCC(rs.getString("SdtNCC"));
                    ncc.setEmailNCC(rs.getString("EmailNCC"));

                    arr.add(ncc);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }finally {
                closeConnection();
            }}
        return arr;
    }
    public boolean addNCC(NCCDTO ncc)  {
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "INSERT INTO NCC values(?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1,ncc.getMaNCC());
                stmt.setString(2,ncc.getTenNCC());
                stmt.setString(3,ncc.getSdtNCC());
                stmt.setString(4,ncc.getEmailNCC());
                if(stmt.executeUpdate()>=1)
                    result = true;

            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Lỗi ở hàm add NCC");
            }finally {
                closeConnection();
            }
        }
        return result;
    }
    public boolean hasMaNCC(String MaNCC){
        boolean result = false;
        if(openConnection()){
            try{
                String sql ="SELECT * FROM NCC WHERE MaNCC ='"+MaNCC+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                result = rs.next();
            }catch (SQLException e){
                System.out.println(e);
            }finally {
                closeConnection();
            }

        }
        return result;
    }
    public boolean deleteNCC(String MaNCC) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "DELETE  FROM NCC WHERE MaNCC ='" + MaNCC + "'";
                Statement stmt = con.createStatement();
                int rowCount = stmt.executeUpdate(sql);
                if (rowCount > 0)
                    result = true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Lỗi ở hàm  delete");
            } finally {
                closeConnection();
            }
        }
        return result;
    }
    public boolean updateNCC(NCCDTO ncc,String MaNCC){
        boolean result = false;
        if(openConnection()){
            try{
                System.out.println(MaNCC);
                String sql = "UPDATE NCC SET MaNCC = ?, TenNCC = ?, SdtNCC = ?, EmailNCC = ?"+" WHERE MaNCC = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1,ncc.getMaNCC());
                stmt.setString(2,ncc.getTenNCC());
                stmt.setString(3,ncc.getSdtNCC());
                stmt.setString(4,ncc.getEmailNCC());
                stmt.setString(5,MaNCC);
                int rowCount = stmt.executeUpdate();
                System.out.println(rowCount);
                if(rowCount > 0)
                    result = true;

            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Lỗi ở hàm Update");
            }finally {
                closeConnection();
            }
        }
        return result;
    }
    public boolean searchMaNCC(Vector<NCCDTO> ncc_arr, String MaNCC){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM NCC WHERE MaNCC = '" + MaNCC + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    NCCDTO ncc = new NCCDTO();
                    ncc.setMaNCC(rs.getString("MaNCC"));
                    ncc.setTenNCC(rs.getString("TenNCC"));
                    ncc.setSdtNCC(rs.getString("SdtNCC"));
                    ncc.setEmailNCC(rs.getString("EmailNCC"));
                    ncc_arr.add(ncc);
                }
                if(ncc_arr.size() > 0)
                    result = true;
            } catch (SQLException ex){
                System.out.println("Lỗi ở hàm searchSP của class SanPhamDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

}
