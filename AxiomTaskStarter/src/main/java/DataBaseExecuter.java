import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataBaseExecuter {
    public static void main(String[] args) {
        String printXml = new XmlConfigCreater().objectXml();

        System.out.println(printXml);
    }

}

