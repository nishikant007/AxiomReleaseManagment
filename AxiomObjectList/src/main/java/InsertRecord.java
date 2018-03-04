import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRecord {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = null;
            String s            = new String();
            StringBuffer sb = new StringBuffer();
            try {
                conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");


                FileReader fr = null;
                try {
                    fr = new FileReader(new File("C:\\Axiom_release_automation\\AxiomReleaseManagment\\AxiomObjectList\\src\\main\\resources\\InsertDummyRecords.sql"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

                BufferedReader br = new BufferedReader(fr);

                while((s = br.readLine()) != null)
                {
                    sb.append(s);
                }
                br.close();

                // here is our splitter ! We use ";" as a delimiter for each request
                // then we are sure to have well formed statements
                String[] inst = sb.toString().split(";");


                Statement st = conn.createStatement();

                for(int i = 0; i<inst.length; i++)
                {
                    // we ensure that there is no spaces before or after the request string
                    // in order to not execute empty statements
                    if(!inst[i].trim().equals(""))
                    {
                        st.executeUpdate(inst[i]);
                        System.out.println(">>"+inst[i]);
                    }
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
