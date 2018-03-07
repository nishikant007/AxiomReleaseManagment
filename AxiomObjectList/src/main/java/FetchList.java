import org.h2.engine.Database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchList {

    Map<String, String> objectVal = new HashMap<String, String>();
    List<Map<String, String>> objectList = new ArrayList<Map<String, String>>();
    public FetchList() {
        getObjectList();
    }

    public List<Map<String, String>> getObjectList() {

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
                            objectVal.clear();
                            objectVal.put("object_name", rs.getString("object_name"));
                            objectVal.put("branch_name", rs.getString("branch_name"));
                            objectVal.put("project_name", rs.getString("project_name"));
                            objectVal.put("comment", rs.getString("comment"));
                         objectList.add(objectVal);

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
        return objectList;
    }
}


