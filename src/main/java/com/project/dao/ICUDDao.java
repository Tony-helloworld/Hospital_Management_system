package com.project.dao;


import com.project.entity.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ICUDDao {
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
            long a = dao.getUsed("ICU");
            System.out.println(a);
            Query q = session.createQuery("update ICUD set patientID= :u, receptionistID = :p where id = :s");
            q.setParameter("u", PID);
            q.setParameter("p", ReceptionistID);
            q.setParameter("s", a);

            int res = q.executeUpdate();
            System.out.println(res);
            System.out.println(a);
            return true;
//
//
//            Room temp= (Room) q.uniqueResult();
//            System.out.println(temp);
//
//            int u = temp.getUsed()+1;
//            int max = temp.getMaxRoom();
//            if(u<=max){
//                Query q1=session.createQuery("update Room set used= :u where name = :p ");
//                q1.setParameter("p", name);
//                q1.setParameter("u", u);
//                int res= q1.executeUpdate();
//                infoLog.logActivities("Add ICU="+u);
//                return true;
//            }
//            else {
//                return false;
//            }

        }
        catch(Exception e)
        {
            System.out.println(e);
            infoLog.logActivities("Add ICU woring: "+e);
            return false;
        }
    }

}
