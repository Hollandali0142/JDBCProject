import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunaTest {

    /*
Given user connects to database
    (hostname: medunna.com, DatabaseName: medunna_db, Username: medunna_user, Password: medunna_pass_987
When user sends the query to get the names of created_by column from "room" table
Then Assert that there are some rooms created by "john_doe"
And user closes the connection
 */
    @Test
    public void medunaTest() throws SQLException {

        // Given user connects to the database
        JdbcUtils.connectToDataBase("medunna.com","medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement= JdbcUtils.createStatement();

        //When user sends the query to get the names of created_by column from "room" table
        String sql="Select created_by From room";

     ResultSet resultSet1= statement.executeQuery(sql);

        List<String>createByname=new ArrayList<>();

        while (resultSet1.next()){

            createByname.add(resultSet1.getString(1));
        }
        System.out.println("createByname = " + createByname);

        //Then Assert that there are some rooms created by "john_doe"

        Assert.assertTrue(createByname.contains("john_doe"));


        //And user closes the connection
            JdbcUtils.closeCannectionVeStatement();
    }
}
