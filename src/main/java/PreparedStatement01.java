import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        //2.adim: Database baglan

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "1985");

        //3.Adim : Statement olustur
        Statement st = con.createStatement();

        /*
        PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL KODUNU TEMSIL EDER.
        PreparedStatement, parametrelendirilmis SQL sorgulari(query) ile calisir.Bu sorguyu 0 yada daha fazla parametre ile kullanabiliriz
         */

        //1. Orn: Prepared Statement kullanarak company adi IBM olan number_of_employees degerini 9999 olarak guncelleyin

            //1. Adim:PreparedStatement Query olustur(parametre yerine ? yazdik)

        String sql1= "Update companies Set number_of_employees =? Where company =?";
        //2. adim PreparedStatement object olustur

       PreparedStatement sp1= con.prepareStatement(sql1);

       //3. adimda : setInt(), setString().... methodlarini kullanarak soru isaretleri yerine deger gonder

        sp1.setInt(1,9999);
        sp1.setString(2,"IBM");
        //4. Adim : Query i calistir

      int guncellenenSatirsayisi= sp1.executeUpdate();
        System.out.println("guncellenenSatirsayisi = " + guncellenenSatirsayisi);

        String sql1a="Select *from companies";
        ResultSet resultSet1=st.executeQuery(sql1a);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company")+"  "+ resultSet1.getString("number_of_employees"));

        }
        //con.close();
       // st.close();
       // resultSet1.close();

    }


}
