import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Adim = Driver a kaydol

        Class.forName("org.postgresql.Driver");

        //2.adim: Database baglan

       Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "1985");

       //3.Adim : Statement olustur
       Statement st= con.createStatement();
        System.out.println("Connection basarili");

        //4. Adim : Query (sorgu)olustur==>zorunlu degildir

      boolean sql1= st.execute("Create Table workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT)");
        System.out.println("sql1 = " + sql1);//false dondurur cunku data cagirmiyoruz
        /*
        execute() methodu DDL -data definition language-(create, drop, alter table) ve DQL -data query language -(select) icin kullanilir
        i)Eger execute() methodu DDL icin kullanilirsa 'false' return eder
        ii)Eger execute() methodu DQL icin kullanilirsa Resulset(sonuc/data/veri) alindiginda 'true' aksi halde 'false' dondurur
         */

        //2. Ornek: Table'a worker_address sutunu ekleyerek alter(degisiklik) yapin

        String sql2="Alter Table workers Add worker_address VARCHAR(80)";
        boolean sql2a=st.execute(sql2);
        System.out.println("sql2a = " + sql2a);

        //3.Ornek: workers table'ini silin
        String sql3="Drop Table workers";
         boolean sq3a= st.execute(sql3);
        System.out.println("sq3a = " + sq3a);

        //5. Adim:
        con.close();
        st.close();

    }
}
