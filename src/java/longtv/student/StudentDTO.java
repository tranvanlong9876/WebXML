/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.student;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class StudentDTO implements Serializable {
    private String id, sClass, fullName, address, sex, status;

    public StudentDTO() {
    }

    public StudentDTO(String id, String sClass, String fullName, String address, String sex, String status) {
        this.id = id;
        this.sClass = sClass;
        this.fullName = fullName;
        this.address = address;
        this.sex = sex;
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the sClass
     */
    public String getsClass() {
        return sClass;
    }

    /**
     * @param sClass the sClass to set
     */
    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "id=" + id + ", sClass=" + sClass + ", fullName=" + fullName + ", address=" + address + ", sex=" + sex + ", status=" + status + '}';
    }
    
    
}
