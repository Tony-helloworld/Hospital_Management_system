package com.project.controller.patient;

import com.project.dao.LoginDao;
import com.project.dao.doctor.PatientHistoryDao;
import com.project.dao.opd.DeleteOpdDao;
import com.project.dao.patient.PPatientPrescriptionDao;
import com.project.entity.Login;
import com.project.entity.OpdDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PPatientPrescriptionController
{
	@Autowired
	PPatientPrescriptionDao dao;
	
	@Autowired
	DeleteOpdDao dao1;
	
	@Autowired
	PatientHistoryDao dao2;
	
	@Autowired
	LoginDao infoLog;
	
	@RequestMapping(value="/prescriptionView.html")
	public ModelAndView prescriptionQueue(HttpServletRequest request)
	{	
		try {
			HttpSession session= request.getSession();
			Login l=(Login)session.getAttribute("userInfo");
			infoLog.logActivities("in PatientPrescriptionController-prescriptionQueue:");
			
			List<String[]> prescriptionList= dao.getPrescriptionList(l.getId());
			
			if(! prescriptionList.equals(null))
			{
				ModelAndView mv= new ModelAndView();
				mv.setViewName("patient/PrescriptionView");
				mv.addObject("prescriptionList", prescriptionList);
				mv.addObject("prescriptionsCount", dao.prescriptionPrintCount2(l.getId()));  //for receptionist only
				return mv;
			}
			else
			{   throw new Exception();  }
		}
		catch(Exception e)
		{
			infoLog.logActivities("in PatientPrescriptionController-prescriptionQueue: "+e);
			ModelAndView mv= new ModelAndView();
			mv.setViewName("failure");
			mv.addObject("error",e);
			return mv;
		}
	}
	
	@RequestMapping("/1printPrescription.html")
	public ModelAndView print(@RequestParam("pid")String pid, @RequestParam("opdid")String opdid)
	{	
		try 
		{
			infoLog.logActivities("in PatientPrescriptionController-print: got="+pid+" "+opdid);
			int opdId=Integer.parseInt(opdid);
//			System.out.println(opdId);
//			System.out.println(pid);
			String name=dao.getPatientName(pid);
//			System.out.println(name);
			infoLog.logActivities("returned to PatientPrescriptionController-print: got="+name);
//			System.out.println("------------------------------------------");
			OpdDetails data=dao2.showHistory(opdId);

			System.out.println(data);

			infoLog.logActivities("returned to PatientPrescriptionController-print: got="+data);
			
			if( (! name.equals(null)) && (data.getOpdid()!=0 ) )
			{
				ModelAndView mv= new ModelAndView();
				mv.setViewName("receptionist/PrescriptionPrintView");
				mv.addObject("patientName", name);
				mv.addObject("prescription", data);
				return mv;
			}
			else
			{   throw new Exception();  }
		}
		catch(Exception e)
		{
			System.out.println(e);
			infoLog.logActivities("in PatientPrescriptionController-print: "+e);
			ModelAndView mv= new ModelAndView();
			mv.setViewName("failure");
			mv.addObject("error",e);
			return mv;
		}
	
	}
	
	@RequestMapping(value = "/1prescriptionPrintDone.html", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("pid")String pid)
	{
		try 
		{
			infoLog.logActivities("in PatientPrescriptionController-delete: got="+pid);
			
			int i=dao1.prescriptionPrintDone(pid);
			infoLog.logActivities("returned to PatientPrescriptionController-delete: got="+i);
			
			if(i==1)
			{
				List<String[]> prescriptions= dao.getPrescriptionList("1");
				int count=dao.prescriptionPrintCount();
				
				if(! prescriptions.equals(null))
				{
					ModelAndView mv= new ModelAndView();
					mv.setViewName("receptionist/PrescriptionQueueView");
					mv.addObject("prescriptionList", prescriptions);
					mv.addObject("prescriptionsCount", count);  //for receptionist only
					return mv;
				}
				else
				{   throw new Exception();  }
				
			}
			else
			{   throw new Exception();  }
		}
		catch(Exception e)
		{
			infoLog.logActivities("in PatientPrescriptionController-delete: "+e);
			ModelAndView mv= new ModelAndView();
			mv.setViewName("failure");
			mv.addObject("error",e);
			return mv;
		}
	}
	
}
