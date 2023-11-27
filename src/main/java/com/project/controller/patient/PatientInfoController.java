package com.project.controller.patient;

import com.project.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.LoginDao;
import com.project.dao.receptionist.PatientPrescriptionDao;
import com.project.dao.receptionist.SearchPatientDao;
import com.project.entity.Employee;
import com.project.entity.Patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PatientInfoController
{
    @Autowired
    SearchPatientDao dao;

    @Autowired
    PatientPrescriptionDao dao1;

    @Autowired
    LoginDao infoLog;

    @RequestMapping(value="/patientInfo.html", method = RequestMethod.GET)
    public ModelAndView searchId(HttpServletRequest request)
    {
        HttpSession session= request.getSession();
        Login l=(Login)session.getAttribute("userInfo");
//        System.out.println(l.getRole());
//        System.out.println(l.getId());
        try {
            infoLog.logActivities("in SearchPatientController-searchId: got= "+l.getId());
            Patient p1= dao.searchId(l.getId());
            infoLog.logActivities("returned to in SearchPatientController-searchId: got= "+p1);

            String doctorAssigned=dao.searchDoctorAssigned(p1.getDoctorId());
            infoLog.logActivities("returned to in SearchPatientController-searchId: got= "+doctorAssigned);

            if(!(p1.getPid().equals(null)) && !(doctorAssigned.equals(null)))
            {

                ModelAndView mv= new ModelAndView();
                mv.setViewName("patient/PatientDetailsView");
                mv.addObject("patient", p1);
                mv.addObject("doctorAssigned", doctorAssigned);
                mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount2(l.getId()));  //for receptionist only
                return mv;
            }
            else
            {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            infoLog.logActivities("in SearchPatientController-searchId: "+e);
            System.out.println(e);
            ModelAndView mv= new ModelAndView();
            mv.setViewName("receptionist/SearchPatientView");
            mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount());  //for receptionist only
            mv.addObject("status","false");
            return mv;
        }
    }


}
