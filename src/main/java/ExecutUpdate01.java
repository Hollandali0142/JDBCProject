import java.sql.*;

public class ExecutUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        //2.adim: Database baglan

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "1985");

        //3.Adim : Statement olustur
        Statement st = con.createStatement();

        //1.ornek: number_of_employees degeri ortalama calisan sayisindan az olan number_of_employees degerlerini 16000 olarak UPDATE edin

        String  sql1="Update companies Set number_of_employees=16000 Where number_of_employees<(Select Avg(number_of_employees) From companies)";
       int UpdateEdilenSatirSayisi= st.executeUpdate(sql1);
        System.out.println("UpdateEdilenSatirSayisi = " + UpdateEdilenSatirSayisi);
        String sql1a="Select *from companies";

           ResultSet resultSet1= st.executeQuery(sql1a);

           while (resultSet1.next()){
               System.out.println(resultSet1.getString("company")+"  "+ resultSet1.getString("number_of_employees"));

           }
           con.close();
           st.close();
           resultSet1.close();

    }
}