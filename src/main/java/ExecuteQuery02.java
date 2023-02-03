import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        //2.adim: Database baglan

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "1985");

        //3.Adim : Statement olustur
        Statement st = con.createStatement();

        //1. ornek: companies tablosundan en yuksek ikinci number_of_employees degeri olanin company ve number_of_employees degerlerini yazdirin

            // 1.yol
        String sql1="Select company, number_of_employees From companies Order By number_of_employees DESC Offset 1 Row Fetch Next Row Only";
       ResultSet rs1=st.executeQuery(sql1);
       while (rs1.next()){
           System.out.println(rs1.getString("company")+"  "+rs1.getString("number_of_employees"));

       }

        //2.yol
        String sql2="Select company, number_of_employees From companies Where number_of_employees = (Select Max(number_of_employees) From companies Where number_of_employees < (Select Max(number_of_employees) From companies))";
          ResultSet rs2= st.executeQuery(sql2);

          while (rs2.next()){
              System.out.println(rs2.getString("company")+"  "+ rs2.getString("number_of_employees"));

          }
    con.close();
          st.close();
    }

}
