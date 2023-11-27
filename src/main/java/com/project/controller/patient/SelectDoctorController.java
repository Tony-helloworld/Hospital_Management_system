package com.project.controller.patient;

import java.util.List;

import com.project.dao.opd.AddOpdDao;
import com.project.entity.Login;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.LoginDao;
import com.project.dao.receptionist.AddPatientDao;
import com.project.dao.receptionist.PatientPrescriptionDao;
import com.project.entity.Address;
import com.project.entity.Name;
import com.project.entity.Patient;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.EditLoginDetailsDao;
import com.project.entity.Login;
import com.project.entity.Register;
import com.project.dao.LoginDao;

@Controller
public class SelectDoctorController
{
    @Autowired
    AddPatientDao dao;

    @Autowired
    PatientPrescriptionDao dao1;

    @Autowired
    AddOpdDao dao2;
    @Autowired
    LoginDao infoLog;

    @RequestMapping(value="/selectDoctor.html", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request)
    {
        try {
            List<String[]> doctors= dao.getDoctors();
            infoLog.logActivities("in AddPatientController-view:got= ");
            HttpSession session= request.getSession();
            Login l=(Login)session.getAttribute("userInfo");
            for(String[] str: doctors)
            {
                infoLog.logActivities(str[0]+", "+str[1]+", "+str[2]+", "+str[3]+", "+str[4]+", ");
            }
            if(! doctors.equals(null))
            {
                ModelAndView mv= new ModelAndView();
                mv.setViewName("patient/SelectDoctorView");
                mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount2(l.getId()));  //for receptionist only
                mv.addObject("doctorsList", doctors);
                return mv;
            }
            else
            {   throw new Exception();  }

        }
        catch(Exception e)
        {
            infoLog.logActivities("in AddPatientController-view: "+e);
            ModelAndView mv= new ModelAndView();
            mv.setViewName("failure");
            mv.addObject("error",e);
            return mv;
        }
    }

    @RequestMapping(value="/updateDoctor.html", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam("firstName")String firstName, @RequestParam("middleName")String middleName, @RequestParam("lastName")String lastName, @RequestParam("birthdate")String birthdate, @RequestParam("gender")String gender, @RequestParam("email")String email, @RequestParam("mobileNo")Long mobileNo, @RequestParam("adharNo")Long adharNo, @RequestParam("country")String country, @RequestParam("state")String state, @RequestParam("city")String city, @RequestParam("residentialAddress")String residentialAddress, @RequestParam("permanentAddress")String permanentAddress, @RequestParam("bloodGroup")String bloodGroup, @RequestParam("chronicDiseases")String chronicDiseases, @RequestParam("medicineAllergy")String medicineAllergy, @RequestParam("doctorId")String doctorId, HttpServletRequest request)
    {
        //try{
        HttpSession session= request.getSession();
        Login l=(Login)session.getAttribute("userInfo");


        Name n1= new Name(firstName, middleName, lastName);
        Address a1= new Address(residentialAddress,permanentAddress);
        infoLog.logActivities("in AddPatientController-add: got= "+n1+" "+birthdate+" "+gender+" "+email+" "+mobileNo+" "+adharNo+" "+country+" "+state+" "+city+" "+a1+" "+bloodGroup+" "+chronicDiseases+" "+medicineAllergy+" "+doctorId);

        Patient p1= new Patient(n1,birthdate,gender,email,mobileNo,adharNo,country,state,city,a1,bloodGroup,chronicDiseases,medicineAllergy,doctorId);
        boolean b=dao.add(p1);
        infoLog.logActivities("returned to AddPatientController-add: got= "+b);
        if(b)
        {
            ModelAndView mv= new ModelAndView();
            mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount2(l.getId()));  //for receptionist only
            mv.setViewName("welcome");
            return mv;
        }
        //else
        //{   throw new Exception();  }
			/*}
			catch(Exception e)
			{
				infoLog.logActivities("in AddPatientController-add: "+e);
				ModelAndView mv= new ModelAndView();
				mv.setViewName("failure");
				mv.addObject("error",e);
				return mv;
			}*/
        return new ModelAndView();
    }
}
