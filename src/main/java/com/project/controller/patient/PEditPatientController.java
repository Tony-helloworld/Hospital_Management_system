package com.project.controller.patient;

import com.project.dao.LoginDao;
import com.project.dao.receptionist.*;
import com.project.entity.Address;
import com.project.entity.Name;
import com.project.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller 
public class PEditPatientController
{
	@Autowired
	PatientDetailsDao dao1;
	@Autowired
	SearchPatientDao dao3;
	@Autowired
	EditPatientDao dao2;
	@Autowired
	AddPatientDao dao4;
	@Autowired
	PatientPrescriptionDao dao5;
	@Autowired
	LoginDao infoLog;
	
	@RequestMapping(value="/1editPatientView.html", method=RequestMethod.POST)
	public ModelAndView edit(@RequestParam("pid")String pid)
	{
		try {
			infoLog.logActivities("in EditPatientController-edit: got= "+pid);
			
			Patient temp=  dao1.show(pid);
			infoLog.logActivities("returned to EditPatientController-edit: got= "+temp);
			
			String doctorname=dao3.searchDoctorAssigned(temp.getDoctorId());
			infoLog.logActivities("returned to EditPatientController-edit: got= "+doctorname);
			
				if(!(temp.getPid().equals(null)) && !(doctorname.equals(null)))
				{
					ModelAndView mv= new ModelAndView();
					mv.setViewName("receptionist/EditPatientView");
					mv.addObject("patient",temp);
					mv.addObject("doctor",doctorname);
					mv.addObject("doctorsList", dao4.getDoctors());
					mv.addObject("prescriptionsCount", dao5.prescriptionPrintCount2(pid));  //for receptionist only
//					mv.addObject("prescriptionsCount", dao5.prescriptionPrintCount());  //for receptionist only

					return mv;
				}
				else
				{   throw new Exception();  }
			}
			catch(Exception e)
			{
				infoLog.logActivities("in EditPatientController-edit: "+e);
				ModelAndView mv= new ModelAndView();
				mv.setViewName("failure");
				mv.addObject("error",e);
				return mv;
			}
				
	}

	@RequestMapping(value="/1editPatient.html", method = RequestMethod.POST)
	public ModelAndView edit(@RequestParam("pid")String pid, @RequestParam("firstName")String firstName, @RequestParam("middleName")String middleName, @RequestParam("lastName")String lastName, @RequestParam("birthdate")String birthdate, @RequestParam("gender")String gender, @RequestParam("email")String email, @RequestParam("mobileNo")Long mobileNo, @RequestParam("adharNo")Long adharNo, @RequestParam("country")String country, @RequestParam("state")String state, @RequestParam("city")String city, @RequestParam("residentialAddress")String residentialAddress, @RequestParam("permanentAddress")String permanentAddress, @RequestParam("bloodGroup")String bloodGroup, @RequestParam("chronicDiseases")String chronicDiseases, @RequestParam("medicineAllergy")String medicineAllergy, @RequestParam("doctorId")String doctorId, HttpServletRequest request)
	{
		try {
			Name n1=new Name(firstName,middleName,lastName);
			Address a1= new Address(residentialAddress, permanentAddress);
			infoLog.logActivities("in EditPatientController-edit: got= "+pid+" "+n1+" "+birthdate+" "+gender+" "+email+" "+mobileNo+" "+adharNo+" "+country+" "+state+" "+city+" "+a1+" "+bloodGroup+" "+chronicDiseases+" "+medicineAllergy+" "+doctorId);
			String n2 = firstName+middleName+lastName;
			HttpSession session= request.getSession();
			session.setAttribute("Name", n2);
			int i=dao2.edit(pid,n1,birthdate,gender,email,mobileNo,adharNo,country,state,city,a1,bloodGroup,chronicDiseases,medicineAllergy,doctorId);
			infoLog.logActivities("returned to EditPatientController-edit: got= "+i);
			
				if(i==1)
				{
					ModelAndView mv= new ModelAndView();
					mv.setViewName("successPage");

					mv.addObject("prescriptionsCount", dao5.prescriptionPrintCount2(pid));  //for receptionist only
					return mv;
				}
				else
				{   throw new Exception();  }
			}
			catch(Exception e)
			{
				infoLog.logActivities("in EditPatientController-edit: "+e);
				ModelAndView mv= new ModelAndView();
				mv.setViewName("failure");
				mv.addObject("error",e);
				return mv;
			}
	}
}
