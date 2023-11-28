package com.project.dao;


import com.project.entity.Pharmacy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PharmacyDao {
    @Autowired
    SessionFactory sf;//hibernate configuration in springMVC-servlet.xml file

    @Autowired
    LoginDao infoLog;

    @Transactional
    public boolean AddPharmacy(String name)
    {
        try
        {
            infoLog.logActivities("Add ICU= ");
//            System.out.println(name);
            Session session= sf.getCurrentSession();

//            String hashStr=passsword;
            Query q = session.createQuery("from Pharmacy where name = :p");
            q.setParameter("p", name);
            Pharmacy temp= (Pharmacy) q.uniqueResult();
//            System.out.println(temp);

            int u = temp.getDrugNumber()+1;
//            int max = temp.getMaxRoom();
                Query q1=session.createQuery("update Pharmacy set drugNumber= :u where name = :p ");
                q1.setParameter("p", name);
                q1.setParameter("u", u);
                int res= q1.executeUpdate();
                infoLog.logActivities("Add ICU="+u);
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

    public boolean MinusPharmacy(String name)
    {
        try
        {
            infoLog.logActivities("Add ICU= ");

            Session session= sf.getCurrentSession();
//            String hashStr=passsword;
            Query q = session.createQuery("from Pharmacy where name = :p");
            q.setParameter("p", name);
            Pharmacy temp= (Pharmacy) q.uniqueResult();
            int u = temp.getDrugNumber()-1;
//            int max = temp.getMaxRoom();
            if(u>=0){
                Query q1=session.createQuery("update Pharmacy set drugNumber= :u where name = :p ");
                q1.setParameter("p", name);
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
    public List<Pharmacy> getAllPharmacys()
    {
        try {
            infoLog.logActivities("in AllEmployeeDetailsDao-getAllEmployees: ");
            Session session= sf.getCurrentSession();
            Query q1= session.createQuery("from Pharmacy ");	//HQL use classname not tablename
//            q1.setParameter("s", 1);
            List<Pharmacy> l1=(List<Pharmacy>) q1.list();
            return l1;
        }
        catch(Exception e)
        {
            infoLog.logActivities("in AllEmployeeDetailsDao-getAllEmployees: "+e);
            return null;
        }
    }
    @Transactional
    public Pharmacy show(long id)
    {
        try {
            infoLog.logActivities("in AllEmployeeDetailsDao-getAllEmployees: ");
            Session session= sf.getCurrentSession();
            Query q1= session.createQuery("from Pharmacy where id =:s");	//HQL use classname not tablename
            q1.setParameter("s", id);
            Pharmacy temp= (Pharmacy) q1.uniqueResult();
            return temp;
        }
        catch(Exception e)
        {
            infoLog.logActivities("in AllEmployeeDetailsDao-getAllEmployees: "+e);
            return null;
        }
    }
    @Transactional
    public int edit(int having, String name2){
//        try {
//            if (used2>max2){throw new Exception();}
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//            infoLog.logActivities("in logindao-validate: "+e);
//            return 0;
//        }
        try {
            Session session= sf.getCurrentSession();

//            Query q1=session.createQuery("update Room set used= :u where name = :p ");
////            Query q1=session.createQuery("update Room set used = :p,max= :q where id = :s ");
//            q1.setParameter("u", used2);
//            q1.setParameter("p", name2);
            Query q1=session.createQuery("update Pharmacy set drugNumber= :u where name = :p ");
            q1.setParameter("p", name2);
            q1.setParameter("u", having);
            int res= q1.executeUpdate();
//
//            System.out.println("---------------------5");
//            System.out.println(used2);
//            System.out.println(max2);
//            System.out.println(name2);
//
//            Query q2=session.createQuery("update Room set maxRoom= :u where name = :p ");
//            q2.setParameter("p", name2);
//            q2.setParameter("u", max2);
//            res= q2.executeUpdate();
//            System.out.println("---------------------6");

            infoLog.logActivities("in EditEmployeeDao-edit: found= "+res);
            return res;
        }
        catch(Exception e)
        {
            System.out.println(e);
            infoLog.logActivities("in logindao-validate: "+e);
            return 0;
        }

    }
    @Transactional
    public int getdrugNumber( String name){
        Session session= sf.getCurrentSession();
        Query q1= session.createQuery("from Pharmacy where name =:s");	//HQL use classname not tablename
        q1.setParameter("s", name);
        Pharmacy temp= (Pharmacy) q1.uniqueResult();
        return temp.getDrugNumber();
    }
}
