import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //1. adim : Drivera kaydol

        //2. Adim: Databasebaglan

   Connection con = JdbcUtils.connectToDataBase("localhost","Arcane", "postgres", "1985");

        //3.Adim Statement olustur

      Statement statement= JdbcUtils.createStatement();

      //4. adim :query(sorgu) olustur ve calistir

           // JdbcUtils.execute("Create Table students(name VARCHAR(20),okul_no INT, address VARCHAR(80))");

        JdbcUtils.createTable("mudur1", "name VARCHAR(20)","ili INT ", "ogretmen_ismi VARCHAR(80)", "okul_no INT");

        //5. Adim : Baglanti ve Statement i kapat

        JdbcUtils.closeCannectionVeStatement();

    }
}
