package com.project.dao.doctor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.project.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.dao.LoginDao;
import com.project.entity.Opd;
import com.project.entity.OpdDetails;
import com.project.entity.Patient;

@Component
public class patientObservePrescribeDao 
{
	@Autowired
	SessionFactory sf;
	
	@Autowired
	LoginDao infoLog;
	
	@Transactional
	public int add(OpdDetails patientcase,String pid) 
	{
		infoLog.logActivities("in addpatientcasedao "+patientcase);
		Session session= sf.getCurrentSession();
		Query q1=session.createQuery("from Opd where pid= :i AND status= :s");
		q1.setParameter("i", pid);
		q1.setParameter("s", 1); //pending status
		
		try 
		{
			Opd opd= (Opd) q1.uniqueResult();
			infoLog.logActivities(""+opd);
		
				if(opd.getOpdId()!=0)
				{				
				patientcase.setOpdid(opd.getOpdId());
				
				session.save(patientcase);
				
				int opdid=opd.getOpdId();
				return opdid;
				}	
				else
				{
					throw new Exception();
				}
			}
			catch(Exception e)
			{ 
				infoLog.logActivities("error in finding opdid "+e);
			  return 0;
			}		
	}
	@SuppressWarnings("null")
	@javax.transaction.Transactional
	public List<String[]> getReceptionist()
	{
		Session session= sf.getCurrentSession();
		Query q1= session.createQuery(" from Employee where role= :r AND status=:s");	//HQL use classname not tablename
		q1.setParameter("r", "receptionist");
		q1.setParameter("s", 1);

		try {
			List<Employee> l1=(List<Employee>) q1.list();
			infoLog.logActivities("in AddPatientDao-getDoctors:found= "+l1);

			List<String[]> doctorList = new ArrayList<String[]>();
			int i=0,j=0;

			for(Employee e: l1)
			{
				String[] temp= new String[4];
				temp[0]=e.getEid();
				temp[1]=e.getName().getFirstName();
				temp[2]=e.getName().getMiddleName();
				temp[3]=e.getName().getLastName();
//				temp[4]=e.getEid();
//				System.out.println(temp[4]);
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

}
