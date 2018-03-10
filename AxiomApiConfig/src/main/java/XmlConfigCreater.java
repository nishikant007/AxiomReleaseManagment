import java.util.List;
import java.util.Map;

public class XmlConfigCreater {


    XmlConfigCreater() {

    }

    public String objectXml() {
        List<Map<String, String>> objectList = new FetchList().getObjectList();
        String staticConfigXml = staticXml();
        StringBuffer dynamicXml = new StringBuffer("");
        String finalXML = "";
        objectList.forEach(object -> {
            String nabc = objectXml(object.get("object_name"), object.get("branch_name"), object.get("project_name"), object.get("comment"));
            dynamicXml.append(nabc);
        });

        finalXML = staticConfigXml + dynamicXml;
        return finalXML;
    }

    private String staticXml() {
        return "<component>\n" +
                "    <object-type>\n" +
                "        <property name=\"test xml\"/>\n" +
                "        \n" +
                "    </object-type>\n" +
                "</component>";
    }

    private String objectXml(String objectName, String branchName, String projectName, String comment) {

        return "<object>\n" +
                "    <property name=\"objectName\" value=\"" + objectName + "\"/>\n" +
                "    <property name=\"branchName\" value=\"\"" + branchName + "\"\"/>\n" +
                "    <property name=\"projectName\" value=\"\"" + projectName + "\"\"/>\n" +
                "    <property name=\"comment\" value=\"\"" + comment + "\"\"/>\n" +
                "</object>";
    }

}
