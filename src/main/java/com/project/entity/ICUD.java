package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ICUD {
//    private int iCU;
//    private int maxICU;
//
//    private int operationRoom;
//
//    private int maxOperationRoom;

    private Long id;
    private String receptionistID;

    private String patientID;
//    private String patientName;
    public ICUD(){
    }

    public ICUD(Long id , String receptionistID, String patientIDm )
    {
        super();
        this.id = id;
        this.receptionistID = receptionistID;
        this.patientID =patientID;
//        this.patientName =patientName;
    }
    public void  setReceptionistID( String receptionistID){this.receptionistID = receptionistID;}
    public void  setPatientID( String receptionistID){this.patientID =patientID;}
//    public void setPatientName(String receptionistID){this.patientName= patientName;}
    public String getReceptionistID(){return receptionistID;}
    public String getPatientID(){return patientID;}
//    public String getPatientName(){return patientName;}


    @Override
    public String toString() {
        return "ROOM [ICU=" + receptionistID + ", OperationRoom=" + patientID + id +"]";
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
