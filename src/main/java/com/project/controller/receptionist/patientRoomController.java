package com.project.controller.receptionist;

import com.project.dao.ICUDDao;
import com.project.dao.LoginDao;
import com.project.dao.RoomDao;
import com.project.dao.doctor.PatientHistoryDao;
import com.project.dao.doctor.patientObservePrescribeDao;
import com.project.dao.opd.DeleteOpdDao;
import com.project.dao.receptionist.ORDDao;
import com.project.dao.receptionist.PatientPrescriptionDao;
import com.project.entity.ICUD;
import com.project.entity.Login;
import com.project.entity.ORD;
import com.project.entity.OpdDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class patientRoomController
{
	@Autowired
	patientObservePrescribeDao dao;

	@Autowired
	PatientPrescriptionDao dao1;

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
	@RequestMapping("/ICUInfo.html")
	public ModelAndView ICUView(HttpServletRequest request)
	{
//		request.get
		HttpSession session=request.getSession();
		Login l=(Login)session.getAttribute("userInfo");
		String id = l.getId();


		List<ICUD> icus = dao4.get(id);

		ModelAndView mv= new ModelAndView();

		mv.setViewName("receptionist/ICUInfoView");
//		mv.addObject("Rooms", dao1.getAllRooms());
//		List<String[]> receptionists= dao.();
//		mv.addObject("receptionists", receptionists);
		mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount());  //for receptionist only
		mv.addObject("icus", icus);
		return mv;
	}

    @RequestMapping("/OperationRoomInfo.html")
    public ModelAndView OperationRoomView(HttpServletRequest request)
    {
		HttpSession session=request.getSession();
		Login l=(Login)session.getAttribute("userInfo");
		String id = l.getId();
		List<ORD> icus = dao5.get(id);

        ModelAndView mv= new ModelAndView();

        mv.setViewName("receptionist/OPDInfoView");
		List<String[]> receptionists= dao.getReceptionist();
		mv.addObject("receptionists", receptionists);
		mv.addObject("icus", icus);
		mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount());  //for receptionist only
        return mv;
    }
//	@RequestMapping("/addDopdPatientCase.html")
//	public ModelAndView addPatientCase(@RequestParam("symptoms")String symptoms, @RequestParam("diagnosis")String diagnosis, @RequestParam("medicinesDose")String medicinesDose, @RequestParam("dos")String dos, @RequestParam("donts")String donts, @RequestParam("investigations")String investigations, @RequestParam("followupDate")String followupDate, @RequestParam("fees")int fees, @RequestParam("room") String room, @RequestParam("receptionistId") String receptionistId ,HttpServletRequest request)
//	{	try {
//		infoLog.logActivities("in addpatientcase");
//		// 加病床
//		boolean b=true;
//		if (!room.equals("None")) {b = dao3.AddRoom(room);}
//		if (!b){ throw new Exception();}
////		System.out.println("-------------------");
////		System.out.println(symptoms);
////		System.out.println(diagnosis);
//
//		OpdDetails patientcase= new OpdDetails(symptoms, diagnosis, medicinesDose, dos, donts, investigations, followupDate, fees, room);
//
//		HttpSession session=request.getSession();
//		String pid=(String)session.getAttribute("currentPatientId");
////		System.out.println("-------------------4");
//		if (!room.equals("None"))
//		{
//			if (room.equals("ICU")){
////				System.out.println("-------------------3");
//				dao4.add(pid,receptionistId);
//			}
//			else if(room.equals("OperationRoom")){
//				dao5.add(pid,receptionistId);
//			}
//		}
//		int opdid=dao.add(patientcase,pid);
//		dao1.prescriptionPrint(pid);
//
//
//		ModelAndView mv= new ModelAndView();
//		mv.setViewName("doctor/PrescriptionPrintView");
//		mv.addObject("prescription", dao2.showHistory(opdid));
//		return mv;
//	    }
//		catch(Exception e)
//		{
//			infoLog.logActivities("in PatientHistoryController-showHistoryList: "+e);
//			ModelAndView mv= new ModelAndView();
//			mv.setViewName("failure");
//			mv.addObject("error","There is no more room ");
//			return mv;
//		}
//	}

}
