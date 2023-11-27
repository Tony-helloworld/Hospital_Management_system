package com.project.dao.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.entity.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.LoginDao;
import com.project.entity.Employee;
import com.project.entity.IdGenerate;
import com.project.entity.Login;

import javax.servlet.http.HttpSession;

@Component
public class RegisterDao
{
    @Autowired
    private SessionFactory sf;	//hibernate configuration in springMVC-servlet.xml file

    @Autowired
    LoginDao infoLog;

    @SuppressWarnings("null")
    @javax.transaction.Transactional
    public List<String[]> getDoctors()
    {
        Session session= sf.getCurrentSession();
        Query q1= session.createQuery(" from Employee where role= :r AND status=:s");	//HQL use classname not tablename
        q1.setParameter("r", "doctor");
        q1.setParameter("s", 1);

        try {
            List<Employee> l1=(List<Employee>) q1.list();
            infoLog.logActivities("in AddPatientDao-getDoctors:found= "+l1);

            List<String[]> doctorList = new ArrayList<String[]>();
            int i=0,j=0;

            for(Employee e: l1)
            {
                String[] temp= new String[5];
                temp[0]=e.getEid();
                temp[1]=e.getName().getFirstName();
                temp[2]=e.getName().getMiddleName();
                temp[3]=e.getName().getLastName();
                temp[4]=e.getSpecialization();
                System.out.println(temp[4]);
                doctorList.add(temp);
            }
            infoLog.logActivities("in AddPatientDao-getDoctors:found= "+doctorList);

            return doctorList;
        }
        catch(Exception e)
        {
            infoLog.logActivities("in AddPatientDao-getDoctors: "+e);
            return null;
        }
    }

    //to manage transaction by itself
    @Transactional
    public String add(Patient p1)
    {
        try
        {

            infoLog.logActivities("in AddPatientDao-add: got= "+p1);
            //try {
            Date date= new Date();
            p1.setRegistrationDate(date);  //currentdate is stored

            Session session= sf.getCurrentSession();
            session.save(p1);

            //incrementing eid of idgenerate table contents
            Query q1=session.createQuery(" from IdGenerate");
            IdGenerate temp= (IdGenerate) q1.uniqueResult();

            int pid=temp.getPid();
            infoLog.logActivities("in AddPatientDao-add: pid= "+pid);
            pid++;

            q1=session.createQuery("update IdGenerate set pid= :i");
            q1.setParameter("i", pid);
            int res= q1.executeUpdate();

            infoLog.logActivities("in AddPatientDao-add: incremented pid= "+pid+" update status="+res);


            //storing info in Login table
            String id=p1.getPid();
            String role=p1.getRole();
            String username=p1.getPid();

            String password= String.valueOf(p1.getAdharNo());
            infoLog.logActivities("aadhar no= "+p1.getAdharNo()+", generated hash= "+password);
            Login l= new Login(id, role, username, password);
//            HttpSession session= request.getSession();
//            session.setAttribute("userInfo", l);
            infoLog.logActivities(""+l);
            session.save(l);

            return id;
        }
        catch(Exception ex)
        {
            infoLog.logActivities("in AddEmployeeDao-add: "+ex);
            return null;
        }
    }
}
