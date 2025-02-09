package main.java.DAL;
import main.java.DTO.CTHDDTO;
import java.sql.*;
import java.util.Vector;
public class CTHDDAL {
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
            if(con!=null)
                con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public Vector<CTHDDTO> getAllCTHD(){
        Vector<CTHDDTO> arrHD = new Vector<CTHDDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from CTHD";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    CTHDDTO cthd = new CTHDDTO();
                    cthd.setSP(rs.getString("MaSP"));
                    cthd.setHD(rs.getString("MaHD"));
                    cthd.setSLmua(rs.getInt("SLmua"));
                    cthd.setGiaTien(rs.getFloat("GiaTien"));
                    arrHD.add(cthd);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                closeConnection();
            }
        }
        return arrHD;
    }

public boolean addCTHD(CTHDDTO ct){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Insert into CTHD values(?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1,ct.getSP());
                stmt.setString(2,ct.getHD());
                stmt.setInt(3,ct.getSLmua());
                stmt.setFloat(4,ct.getGiaTien());
                if(stmt.executeUpdate() >= 1)
                    result = true;
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return result;
}

    public boolean hasHoaDonID(String mahd){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "SELECT * FROM HoaDon WHERE MaHD = '" + mahd + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                result = rs.next();
            }catch(SQLException ex){
                System.out.println(ex);
            }finally {
                closeConnection();
            }
        }
        System.out.println("Result của CTHD DAL: " + result);
        return result;
    }

public boolean updateCTHD(CTHDDTO cthd, String ct){
        boolean result = false;
        if(openConnection()){
            try{
                System.out.println(ct);
                String sql = "UPDATE CTHD SET MaSP = ?, MaHD = ?, SLmua= ?, GiaTien= ? WHERE MaHD = ? ";
                 PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, cthd.getSP());
                stmt.setString(2, cthd.getHD());
                stmt.setInt(3,cthd.getSLmua());
                stmt.setFloat(4,cthd.getGiaTien());
                stmt.setString(5,ct);
                int rowCount = stmt.executeUpdate();
                System.out.println(rowCount);
                if(rowCount > 0)
                    result = true;
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return result;
}
public boolean searchCTHD(Vector<CTHDDTO> arrHD, String ct){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "SELECT * FROM CTHD WHERE MaHD = '" + ct + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    System.out.println("trong vòng lặp");
                    CTHDDTO cthd = new CTHDDTO();
                    cthd.setSP(rs.getString("MaSP"));
                    cthd.setHD(rs.getString("MaHD"));
                    cthd.setSLmua(rs.getInt("SLmua"));
                    cthd.setGiaTien(rs.getFloat("GiaTien"));
                    arrHD.add(cthd);
                }
                if(arrHD.size() > 0) {
                    result = true;
                    System.out.println("có bản ghi");
                }
                else {
                    System.out.println("Không có bản ghi");
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally {
                System.out.println("CSDL đang được đóng");
                closeConnection();
            }
        }
        return result;
}

}
