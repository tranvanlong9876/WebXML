/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import longtv.utils.XMLHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Admin
 */
public class StudentDAO implements Serializable {

    public StudentDTO checkLogin(String username, String password, Document document) throws XPathExpressionException {
        StudentDTO dto = null;
//1. Access DOM TREE
        if (document != null) {
            //2. Create XPATH Expression
            String expression = "//student[@id='"
                    + username
                    + "' and normalize-space(password)='"
                    + password
                    + "' and not(normalize-space(status)='dropout')]";
            //3. Create XPATH Object
            XPath xpath = XMLHelper.getXPath();
            //4. Evaluate XPATH expression
            Node student = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
            //5. Process result.
            if(student != null) {
                expression = "lastname";
                String lastName = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "middlename";
                String middleName = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "firstname";
                String firstName = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                String fullName = lastName.trim() + " " + middleName.trim() + " " + firstName.trim();
                expression = "status";
                String status = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "address";
                String address = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "@class";
                String classID = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "sex";
                String sex = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                dto = new StudentDTO(username, classID, fullName, address, sex, status);
            } //end if student has existed;
        } //DOM TREE has existed
        return dto;
    }
    
    private List<StudentDTO> students;

    public List<StudentDTO> getStudents() {
        return students;
    }
    
    public void findStudentByAddress(String searchValue, Document document) throws XPathExpressionException {
        StudentDTO dto;
        if(document != null) {
            String expression = "//student[contains(address,'"
                    + searchValue
                    + "')]";
            XPath xpath = XMLHelper.getXPath();
            NodeList studentList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
            for(int i = 0; i < studentList.getLength(); i++) {
                Node student = studentList.item(i);
                expression = "@id";
                String idStudent = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "lastname";
                String lastName = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "middlename";
                String middleName = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "firstname";
                String firstName = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                String fullName = lastName.trim() + " " + middleName.trim() + " " + firstName.trim();
                expression = "status";
                String status = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "address";
                String address = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "@class";
                String classID = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                expression = "sex";
                String sex = (String) xpath.evaluate(expression, student, XPathConstants.STRING);
                dto = new StudentDTO(idStudent, classID, fullName, address, sex, status);
                if(this.students == null) {
                    this.students = new ArrayList<>();
                }
                this.students.add(dto);
            } //end traversing student List
        } //end DOM TREE has existed
    }
    
    public boolean deleteStudent(String id, Document document, String xmlFile) throws XPathExpressionException, TransformerConfigurationException, TransformerException {
        boolean result = false;
        if(document != null) {
            //1. Find Student
            String express = "//student[@id='"
                    + id
                    + "']";
            XPath xpath = XMLHelper.getXPath();
            Node student = (Node) xpath.evaluate(express, document,XPathConstants.NODE);
            //2. Find Parent
            if(student != null) {
                Node students = student.getParentNode();
                if(students != null) {
                    //3. Remove Child
                    students.removeChild(student);
                    //4. Transformer
                    XMLHelper.transformerDOMToFile(document, xmlFile);
                    result = true;
                }
            } //end student has existed
        } //end DOM TREE has existed
        return result;
    }
    
}
