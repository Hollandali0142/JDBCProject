import java.math.BigDecimal;
import java.sql.*;

public class CallableStatemen01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        javada methodlar return type sahibi olsada olmasada method olarak adlandirilir
        SQL' de ise data return ediliyorsa"function" denir.Returen yapmiyorsa"procedure" olarak adlandirilir
         */



        Class.forName("org.postgresql.Driver");

        //2.adim: Database baglan

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "1985");

        //3.Adim : Statement olustur
        Statement st = con.createStatement();

        //CallableStatemen ile function cagirmayi parametrelendiririz

        //1.adimFuntionin kodunu yaz

        String sql1="Create or Replace Function toplamaF(x Numeric, y Numeric) Returns Numeric language plpgsql As $$ Begin Return x+y; End $$";

        //Create or Replace Function==>functioni olustur veya var ise degistir
        //plpgsql==> producure language postgre sql

        //2. Adim: Function calistir

        st.execute(sql1);

        //3.Adim: Functioni cagir
      CallableStatement cs1= con.prepareCall("{?=call toplamaF(?,?)}");// ilk parametre (?) return type icindir

        //4. adim:Return icin registerOurParameter() methodu kullaniriz parametreler icin set()... methodunu kullaniriz

        cs1.registerOutParameter(1, Types.NUMERIC);
        cs1.setInt(2,6);
        cs1.setInt(3,8);

        //5. adim:execute() ile CallableStatement i calistir
        cs1.execute();

        //6. Adim :Sonucu cagirmak icin return data type bakilir

        BigDecimal toplam= cs1.getBigDecimal(1);
        System.out.println("toplam = " + toplam);
    }
}
