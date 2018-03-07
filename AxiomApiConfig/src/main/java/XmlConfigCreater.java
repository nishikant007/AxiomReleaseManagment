import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlConfigCreater {


        XmlConfigCreater(){

}

    public String objectXml() {
        List<Map<String, String>> objectList = new FetchList().getObjectList();
        String staticConfigXml = staticXml();
        String dynamicXml = "";
        Iterator<Map<String, String>> dynamicConfigXml = objectList.iterator();
        while (dynamicConfigXml.hasNext()) dynamicXml += objectXml(dynamicConfigXml.next().get("object_name"),
                dynamicConfigXml.next().get("branch_name"),
                dynamicConfigXml.next().get("project_name"),
                dynamicConfigXml.next().get("comment"));
        return dynamicXml;
    }

    private String staticXml(){
    return "<component>\n" +
            "    <object-type>\n" +
            "        <property name=\"test xml\"/>\n" +
            "        \n" +
            "    </object-type>\n" +
            "</component>";
}

private String objectXml(String objectName, String branchName, String projectName, String comment){

    return "<object>\n" +
            "    <property name=\"objectName\" value=\""+objectName+"\"/>\n" +
            "    <property name=\"branchName\" value=\"\""+branchName+"\"\"/>\n" +
            "    <property name=\"projectName\" value=\"\""+projectName+"\"\"/>\n" +
            "    <property name=\"comment\" value=\"\""+comment+"\"\"/>\n" +
            "</object>";
}

}
