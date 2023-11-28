package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORD {
//    private int iCU;
//    private int maxICU;
//
//    private int operationRoom;
//
//    private int maxOperationRoom;

    private Long id;
    private String receptionistID;

    private String patientID;

    public ORD(){
    }

    public ORD(Long id , String receptionistID, String patientID)
    {
        super();
        this.id = id;
        this.receptionistID = receptionistID;
        this.patientID =patientID;
    }
    public void  setReceptionistID( String receptionistID){this.receptionistID = receptionistID;}
    public void  setPatientID( String receptionistID){this.patientID =patientID;}
    public String getReceptionistID(){return receptionistID;}
    public String getPatientID(){return patientID;}
    public long plus(){return id+1;}
    @Override
    public String toString() {
        return "ROOM [ICU=" + receptionistID + ", OperationRoom=" + patientID + "]";
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

}
