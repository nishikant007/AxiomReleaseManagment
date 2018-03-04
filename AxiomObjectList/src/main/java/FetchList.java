import org.h2.engine.Database;

import java.io.*;
import java.sql.*;

public class FetchList {
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
                    fr = new FileReader(new File("C:\\Axiom_release_automation\\AxiomReleaseManagment\\AxiomObjectList\\src\\main\\resources\\getObjectList.sql"));
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
                        System.out.println(">>"+inst[i]);
                        ResultSet rs = st.executeQuery(inst[i]);

                        while (rs.next()){
                            String object_name  = rs.getString("object_name");
                            String branch_name  = rs.getString("branch_name");
                            String project_name  = rs.getString("project_name");
                            String comment  = rs.getString("comment");
                            System.out.println(object_name+ " - "+ branch_name+" - "+project_name+" - "+comment);
                        }

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


