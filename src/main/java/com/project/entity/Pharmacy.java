package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pharmacy {
//    private int iCU;
//    private int maxICU;
//
//    private int operationRoom;
//
//    private int maxOperationRoom;

    private Long id;
//    private int maxRoom;
    private int drugNumber;
    private String name;

    public Pharmacy(){
    }

    public Pharmacy(String name , int drugNumber)
    {
        super();
        this.name = name;
        this.drugNumber = drugNumber;
//        this.maxRoom =maxRoom;
    }
//    public void  setUsed(int a){used=a;}
//    public void  setMaxRoom(int a){maxRoom=a;}
    public void  setName(String a){name =a;}
    public void  setDrugNumber(int a){drugNumber =a;}

    //
//    public int getUsed(){return used;}
//    public int getMaxRoom(){return maxRoom;}
    public String getName(){return name;}
    public int getDrugNumber(){return drugNumber;}
    @Override
    public String toString() {
        return "ROOM [ICU=" + name + ", OperationRoom=" + drugNumber + "]";
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
