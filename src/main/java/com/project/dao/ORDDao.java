package com.project.dao;


import com.project.dao.LoginDao;
import com.project.dao.RoomDao;
import com.project.entity.ICUD;
import com.project.entity.ORD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ORDDao {
    @Autowired
    SessionFactory sf;//hibernate configuration in springMVC-servlet.xml file

    @Autowired
    RoomDao dao;
    @Autowired
    LoginDao infoLog;

    @Transactional
    public boolean add(String PID,String ReceptionistID)
    {
        try
        {
            infoLog.logActivities("Add ICU= ");
            Session session= sf.getCurrentSession();
            System.out.println(PID);
            System.out.println(ReceptionistID);
            long a = dao.getUsed("OperationRoom");
            System.out.println(a);
            Query q = session.createQuery("update ORD set patientID= :u, receptionistID = :p where id = :s");
            q.setParameter("u", PID);
            q.setParameter("p", ReceptionistID);
            q.setParameter("s", a);

            int res = q.executeUpdate();
            System.out.println(res);
            System.out.println(a);
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            infoLog.logActivities("Add ICU woring: "+e);
            return false;
        }
    }
    @Transactional
    public List<ORD> get(String PID){
        Session session= sf.getCurrentSession();
        Query q = session.createQuery("FROM ORD where receptionistID = :u");
        q.setParameter("u", PID);
        List<ORD> l1=(List<ORD>) q.list();
//        Query q = session.createQuery("from Room where name = :p");
//        q.setParameter("p", name);
//        Room temp= (Room) q.uniqueResult();
        return  l1;
    }
    @Transactional
    public List<ORD> get2() {
        Session session= sf.getCurrentSession();
        Query q = session.createQuery("FROM ORD where receptionistID !=null ");
//        q.setParameter("u", PID);
        List<ORD> l1=(List<ORD>) q.list();
//        Query q = session.createQuery("from Room where name = :p");
//        q.setParameter("p", name);
//        Room temp= (Room) q.uniqueResult();
        return  l1;
    }
}
