package com.project.controller.doctor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.project.dao.ICUDDao;
import com.project.dao.RoomDao;
import com.project.dao.ORDDao;
import com.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.LoginDao;

import com.project.dao.doctor.PatientHistoryDao;
import com.project.dao.doctor.patientObservePrescribeDao;
import com.project.dao.opd.DeleteOpdDao;

import java.util.List;

@Controller
public class patientObservePrescribeController 
{
	@Autowired
	patientObservePrescribeDao dao;
	
	@Autowired
	DeleteOpdDao dao1;
	
	@Autowired
	PatientHistoryDao dao2;
	@Autowired
	ICUDDao dao4;
	@Autowired
	ORDDao dao5;

	@Autowired
	LoginDao infoLog;

	@Autowired
	RoomDao dao3;
	@RequestMapping("/patientObservePrescribe.html")
	public ModelAndView observationView()
	{
		ModelAndView mv= new ModelAndView();

		mv.setViewName("doctor/patientObservePrescribeView");
		List<String[]> receptionists= dao.getReceptionist();
		mv.addObject("receptionist", receptionists);
		return mv;
	}
	
	@RequestMapping("/addDopdPatientCase.html")
	public ModelAndView addPatientCase(@RequestParam("symptoms")String symptoms, @RequestParam("diagnosis")String diagnosis, @RequestParam("medicinesDose")String medicinesDose, @RequestParam("dos")String dos, @RequestParam("donts")String donts, @RequestParam("investigations")String investigations, @RequestParam("followupDate")String followupDate, @RequestParam("fees")int fees, @RequestParam("room") String room, @RequestParam("receptionistId") String receptionistId ,HttpServletRequest request)
	{	try {
		infoLog.logActivities("in addpatientcase");
		// 加病床
		boolean b=true;
		if (!room.equals("None")) {b = dao3.AddRoom(room);}
		if (!b){ throw new Exception();}
//		System.out.println("-------------------");
//		System.out.println(symptoms);
//		System.out.println(diagnosis);

		OpdDetails patientcase= new OpdDetails(symptoms, diagnosis, medicinesDose, dos, donts, investigations, followupDate, fees, room);

		HttpSession session=request.getSession();
		String pid=(String)session.getAttribute("currentPatientId");
//		System.out.println("-------------------4");
		if (!room.equals("None"))
		{
			if (room.equals("ICU")){
//				System.out.println("-------------------3");
				dao4.add(pid,receptionistId);
			}
			else if(room.equals("OperationRoom")){
				dao5.add(pid,receptionistId);
			}
		}
		int opdid=dao.add(patientcase,pid);
		dao1.prescriptionPrint(pid);


		ModelAndView mv= new ModelAndView();
		mv.setViewName("doctor/PrescriptionPrintView");
		mv.addObject("prescription", dao2.showHistory(opdid));
		return mv;
	    }
		catch(Exception e)
		{
			infoLog.logActivities("in PatientHistoryController-showHistoryList: "+e);
			ModelAndView mv= new ModelAndView();
			mv.setViewName("failure");
			mv.addObject("error","There is no more room ");
			return mv;
		}
	}

}
