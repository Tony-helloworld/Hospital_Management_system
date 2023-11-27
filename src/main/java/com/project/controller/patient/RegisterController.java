package com.project.controller.patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.project.controller.opd.AddOpdController;
import com.project.dao.opd.AddOpdDao;
import com.project.dao.patient.RegisterDao;
import com.project.dao.receptionist.AddPatientDao;
import com.project.dao.receptionist.PatientPrescriptionDao;
import com.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.project.controller.opd.AddOpdController;
import com.project.dao.LoginDao;
import com.project.dao.administrator.AddEmployeeDao;

import javax.servlet.http.HttpSession;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.project.dao.LoginDao;
import com.project.dao.UsersInSystemDao;
import com.project.dao.receptionist.PatientPrescriptionDao;
import com.project.entity.Login;
@Controller
public class RegisterController
{
//    @Autowired
////    AddEmployeeDao dao;

    @Autowired
    RegisterDao dao;

    @Autowired
    PatientPrescriptionDao dao1;


    @Autowired
    LoginDao infoLog;

    @RequestMapping("/register.html")
    public ModelAndView view()
    {
        try
        {
            List<String[]> doctors= dao.getDoctors();
//            System.out.println("我");
            infoLog.logActivities("in AddPatientController-view:got= ");
            for(String[] str: doctors)
            {
                infoLog.logActivities(str[0]+", "+str[1]+", "+str[2]+", "+str[3]+", "+str[4]);
            }
            if(! doctors.equals(null))
            {
                ModelAndView mv= new ModelAndView();
                mv.setViewName("patient/AddPatientView");
//                mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount());  //for receptionist only
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

    @RequestMapping(value="/RegisterPatient.html", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam("firstName")String firstName, @RequestParam("middleName")String middleName, @RequestParam("lastName")String lastName, @RequestParam("birthdate")String birthdate, @RequestParam("gender")String gender, @RequestParam("email")String email, @RequestParam("mobileNo")Long mobileNo, @RequestParam("adharNo")Long adharNo, @RequestParam("country")String country, @RequestParam("state")String state, @RequestParam("city")String city, @RequestParam("residentialAddress")String residentialAddress, @RequestParam("permanentAddress")String permanentAddress,@RequestParam("bloodGroup")String bloodGroup, @RequestParam("chronicDiseases")String chronicDiseases, @RequestParam("medicineAllergy")String medicineAllergy, @RequestParam("doctorId")String doctorId, HttpServletRequest request)
    {
        try
        {
            Name n1= new Name(firstName, middleName, lastName);
            String n2 = firstName+middleName+lastName;
            Address a1= new Address(residentialAddress,permanentAddress);
            infoLog.logActivities("in AddPatientController-add: got= "+n1+" "+birthdate+" "+gender+" "+email+" "+mobileNo+" "+adharNo+" "+country+" "+state+" "+city+" "+a1+" "+bloodGroup+" "+chronicDiseases+" "+medicineAllergy+" ");

            Patient p1= new Patient(n1,birthdate,gender,email,mobileNo,adharNo,country,state,city,a1,bloodGroup,chronicDiseases,medicineAllergy,doctorId);

            String b=dao.add(p1);
//            Login l=new Login(userId,l1.getRole(),l1.getUsername(),null);
            HttpSession session= request.getSession();
            session.setAttribute("Name", n2);
            infoLog.logActivities("returned to AddPatientController-add: got= "+b);
            if(b!=null)
            {
                ModelAndView mv= new ModelAndView();
//                mv.addObject("prescriptionsCount", dao1.prescriptionPrintCount());  //for receptionist only
                mv.addObject("prescriptionsCount",dao1.prescriptionPrintCount2(b));
                mv.setViewName("successRegisterPage");
                return mv;
            }
            else
            {   throw new Exception();  }
        }
        catch(Exception e)
        {
            infoLog.logActivities("in AddPatientController-add: "+e);
            ModelAndView mv= new ModelAndView();
            mv.setViewName("failure");
            mv.addObject("error",e);
            return mv;
        }

    }

}
