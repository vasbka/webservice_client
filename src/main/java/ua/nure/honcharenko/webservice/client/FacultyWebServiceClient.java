package ua.nure.honcharenko.webservice.client;

import ua.nure.honcharenko.entity.faculty.Faculty;
import ua.nure.honcharenko.entity.faculty.FacultyBranch;
import ua.nure.honcharenko.webservice.service.FacultyWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.math.BigInteger;
import java.net.URL;

public class FacultyWebServiceClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:9000/faculty?wsdl");
        QName qname = new QName("http://nure.ua/honcharenkoilchishen/service",
                "Faculties");

        Service service = Service.create(url, qname);

        FacultyWebService facultyService = service.getPort(FacultyWebService.class);

        Faculty faculty = new Faculty();
        faculty.setId(2);
        faculty.setTitle("Custom title");
        faculty.setShortTitle("Custom short title");
        faculty.setBudgetPlaceCount(10);
        faculty.setPlaceCount(15);
        Faculty.FacultyRoomNumber facultyRoomNumber = new Faculty.FacultyRoomNumber();
        facultyRoomNumber.setYes("211");
        faculty.setFacultyRoomNumber(facultyRoomNumber);
        Faculty.Additional additional = new Faculty.Additional();
        Faculty.Additional.BacheloerCount bacheloerCount = new Faculty.Additional.BacheloerCount();
        bacheloerCount.setValue(new BigInteger("25"));
        bacheloerCount.setYear(2015);
        faculty.setFacultyBranch(FacultyBranch.PHILOLOGY);
        additional.setBacheloerCount(bacheloerCount);
        additional.setEmployedStudents(new BigInteger("50"));
        faculty.setAdditional(additional);
        Faculty faculty1 = facultyService.createFaculty(faculty);
        System.out.println("Faculty with title : " + faculty.getTitle() + " added successfully.");
        facultyService.deleteFaculty(faculty1.getId());
        System.out.println("Faculty with id : " + faculty1.getId() + " removed successfully");
        System.out.println("Try to get faulcty by name 'he' which already is present : " + facultyService.geFacultyByName("he"));
        System.out.println("Try to get fauclty by name which does not present in xml file: " + facultyService.geFacultyByName("doesNotPresentTitle"));
        System.out.println("Try to get faculty by id 2 : " + facultyService.getFacultyById(1));
        Faculty facultyById = facultyService.getFacultyById(1);
        facultyById.setTitle("First faculty");
        System.out.println("Try to update faculty with id 2. Change name from 'he' to 'FirstFaculty' :" + facultyService.updateFacultyTitle(facultyById));
        System.out.println("Get faculty with id 2 after update : " + facultyService.getFacultyById(1));
    }
}
