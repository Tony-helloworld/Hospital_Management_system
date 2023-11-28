package com.project.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
@Entity

public class Room {
    private int iCU;
    private int maxICU;

    private int operationRoom;

    private int maxOperationRoom;

    private Long id;

    public Room(){
        iCU=10;
        operationRoom=8;
    }

    public Room(int ICU, int operationRoom)
    {
        super();
        this.iCU = ICU;
        this.operationRoom= operationRoom;
    }




    public void  setiCU(int a){iCU=a;}
    public void  setoperationRoom(int a){operationRoom=a;}
    public void  setmaxICU(int a){maxICU=a;}
    public void  setmaxOperationRoom(int a){maxOperationRoom=a;}
    public int getiCU() {
        return iCU;
    }
    public int getoperationRoom() {
        return operationRoom;
    }




    public int  getmaxICU(){ return maxICU; }





    public int  getmaxOperationRoom(){return maxOperationRoom;}




    @Override
    public String toString() {
        return "ROOM [ICU=" + iCU + ", OperationRoom=" + operationRoom + "]";
    }







    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
