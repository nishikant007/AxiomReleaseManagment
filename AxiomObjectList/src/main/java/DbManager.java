import java.sql.SQLException;

import org.h2.tools.Server;

public class DbManager {

    private  String command;

    DbManager(String command){
        this.command = command;
        if(this.command.equalsIgnoreCase("start")) {
            try {
                startDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (this.command.equalsIgnoreCase("stop")) {
            try {
                stopDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startDB() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
        System.out.println("server started");
    }

    private static void stopDB() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9093", "", true, true);
        System.out.println("server stopped");
    }


    public static void main(String[] args) {
        new DbManager("start");
    }

    }

