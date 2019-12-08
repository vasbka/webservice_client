package ua.nure.honcharenko.webservice.client;

import ua.nure.honcharenko.webservice.service.FacultyWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class FacultyWebServiceClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:1986/wss/faculty?wsdl");
        QName qname = new QName("http://impl.webservice.honcharenkoilchishen.nure.ua/",
                "FacultyWebServiceImplService");

        Service service = Service.create(url, qname);

        QName portName = new QName("http://impl.webservice.honcharenkoilchishen.nure.ua/","FacultyWebServiceImplPort");
        FacultyWebService hello = service.getPort(portName, FacultyWebService.class);


        System.out.println(hello.geFacultyByName("someaq"));
    }
}
