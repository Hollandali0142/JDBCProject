import java.sql.*;

public class ExecutQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adim = Driver a kaydol

        Class.forName("org.postgresql.Driver");

        //2.adim: Database baglan

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "1985");

        //3.Adim : Statement olustur
        Statement st = con.createStatement();

        //1.ornek -->region id'si 1 olan "country name" degerlerini cagirin
        String SqL1 = "SELECT country_name from countries where region_id=1";

        boolean sonuc = st.execute(SqL1);

        System.out.println("sonuc = " + sonuc);

        //Record/satirlari gormek icin ExecuteQuery() methodunu kullanmaliyiz.

        ResultSet rS1 = st.executeQuery(SqL1);
        System.out.println("rS1 = " + rS1);

        while (rS1.next()) {
            rS1.getString(1);//veya rS1.getString("country_name")
            System.out.println(rS1.getString(1));
        }

        //2. orn: "region_id'nin 2'den buyuk oldugu "country_id" ve "country_name" degerlerini cagirin

        String sql2 = "SELECT country_id,country_name from countries where region_id>2";
        ResultSet rs2 = st.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getString("country_id")+"--"+rs2.getString("country_name"));

        }

        //3. orn:"number_of_employees" degeri endusuk olan satirin degerlerini yazdirin

        //String sql3="Select *From companies Where number_of_employees =10000";//10000 olan rakam hardcoding onun yerine asagidakini yaz
        String sql3a="Select *From companies Where number_of_employees =(Select Min(number_of_employees) From companies)";

            ResultSet rs3=st.executeQuery(sql3a);

            while (rs3.next()){

                System.out.println(rs3.getString(1)+"--"+ rs3.getString(2)+"--"+rs3.getString(3));

            }
           con.close();
            st.close();
            rs3.close();
    }

}

