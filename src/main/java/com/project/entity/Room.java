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
//    private int iCU;
//    private int maxICU;
//
//    private int operationRoom;
//
//    private int maxOperationRoom;

    private Long id;
    private int maxRoom;
    private int used;
    private String name;

    public Room(){
    }

    public Room(String name ,int used, int maxRoom)
    {
        super();
        this.name = name;
        this.used = used;
        this.maxRoom =maxRoom;
    }
    public void  setUsed(int a){used=a;}
    public void  setMaxRoom(int a){maxRoom=a;}
    public void  setName(String a){name =a;}

    public int getUsed(){return used;}
    public int getMaxRoom(){return maxRoom;}
    public String getName(){return name;}
    @Override
    public String toString() {
        return "ROOM [ICU=" + maxRoom + ", OperationRoom=" + used + "]";
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
