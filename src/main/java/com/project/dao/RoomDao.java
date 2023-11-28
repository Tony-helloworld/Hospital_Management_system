package com.project.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.entity.Login;
import com.project.entity.Room;

import javax.transaction.Transactional;

@Component
public class RoomDao {
    @Autowired
    SessionFactory sf;//hibernate configuration in springMVC-servlet.xml file

    @Autowired
    LoginDao infoLog;

    @Transactional
    public boolean AddICU()
    {
        try
        {
            infoLog.logActivities("Add ICU= ");

            Session session= sf.getCurrentSession();
//            String hashStr=passsword;
            Query q = session.createQuery("from Room ");
            Room temp= (Room) q.uniqueResult();
            int u = temp.getiCU()+1;
            int max = temp.getmaxICU();
            if(u<=max){
                Query q1=session.createQuery("update Room set iCU= :u");
                q1.setParameter("u", u);
                int res= q1.executeUpdate();
                infoLog.logActivities("Add ICU="+u);
                return true;
            }
            else {
                return false;
            }

        }
        catch(Exception e)
        {
            infoLog.logActivities("Add ICU woring: "+e);
            return false;
        }
    }
    @Transactional
    public boolean AddOperationRoom()
    {
        try
        {
            infoLog.logActivities("Add OperationRoom= ");

            Session session= sf.getCurrentSession();
//            String hashStr=passsword;
            Query q = session.createQuery("from Room ");
            Room temp= (Room) q.uniqueResult();
            int u = temp.getoperationRoom()+1;
            int max = temp.getmaxOperationRoom();
            if(u<=max){
                Query q1=session.createQuery("update Room set operationRoom = :u");
                q1.setParameter("u", u);
                int res= q1.executeUpdate();
                infoLog.logActivities("Add OperationRoom="+u);
                return true;
            }
            else {
                return false;
            }

        }
        catch(Exception e)
        {
            infoLog.logActivities("Add ICU operationRoom: "+e);
            return false;
        }
    }
    public boolean MinusICU()
    {
        try
        {
            infoLog.logActivities("Add ICU= ");

            Session session= sf.getCurrentSession();
//            String hashStr=passsword;
            Query q = session.createQuery("from Room ");
            Room temp= (Room) q.uniqueResult();
            int u = temp.getiCU()-1;
            int max = temp.getmaxICU();
            if(u>=0){
                Query q1=session.createQuery("update Room set iCU= :u");
                q1.setParameter("u", u);
                int res= q1.executeUpdate();
                infoLog.logActivities("Add ICU="+u);
                return true;
            }
            else {
                return false;
            }

        }
        catch(Exception e)
        {
            infoLog.logActivities("Add ICU woring: "+e);
            return false;
        }
    }

    @Transactional
    public boolean MinusOperationRoom()
    {
        try
        {
            infoLog.logActivities("Add OperationRoom= ");

            Session session= sf.getCurrentSession();
//            String hashStr=passsword;
            Query q = session.createQuery("from Room ");
            Room temp= (Room) q.uniqueResult();
            int u = temp.getoperationRoom()-1;
            int max = temp.getmaxOperationRoom();
            if(u>=0){
                Query q1=session.createQuery("update Room set operationRoom = :u");
                q1.setParameter("u", u);
                int res= q1.executeUpdate();
                infoLog.logActivities("Add OperationRoom="+u);
                return true;
            }
            else {
                return false;
            }

        }
        catch(Exception e)
        {
            infoLog.logActivities("Add ICU operationRoom: "+e);
            return false;
        }
    }
    @Transactional
    public boolean SetmaxICU(int max)
    {
        try
        {
            infoLog.logActivities("Add ICU= ");

            Session session= sf.getCurrentSession();
//            String hashStr=passsword;
                Query q1=session.createQuery("update Room set maxICU= :u");
                q1.setParameter("u", max);
                int res= q1.executeUpdate();
                infoLog.logActivities("Add ICU="+max);
                return true;
        }
        catch(Exception e)
        {
            infoLog.logActivities("Add ICU woring: "+e);
            return false;
        }
    }
    @Transactional
    public boolean SetmaxOperatinRoom(int max)
    {
        try
        {
            infoLog.logActivities("Add ICU= ");

            Session session= sf.getCurrentSession();
//            String hashStr=passsword;
            Query q1=session.createQuery("update Room set maxOperationRoom= :u");
            q1.setParameter("u", max);
            int res= q1.executeUpdate();
            infoLog.logActivities("Add ICU="+max);
            return true;
        }
        catch(Exception e)
        {
            infoLog.logActivities("Add ICU woring: "+e);
            return false;
        }
    }

}
