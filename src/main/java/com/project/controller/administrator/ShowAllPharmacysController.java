package com.project.controller.administrator;


import com.project.dao.LoginDao;
import com.project.dao.PharmacyDao;
import com.project.dao.administrator.EmployeeDetailsDao;
import com.project.dao.receptionist.PatientPrescriptionDao;
import com.project.entity.Pharmacy;
import com.project.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowAllPharmacysController {
    @Autowired
    PharmacyDao dao1;
    @Autowired
    EmployeeDetailsDao dao2;
    @Autowired
    PatientPrescriptionDao dao;

    @Autowired
    LoginDao infoLog;

    @RequestMapping("/PharmacyInfo2.html")
    public ModelAndView view()
    {
//        System.out.println("---------------------0");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("administrator/AllPharmacysView");
//        System.out.println("---------------------1");
//        dao1.getAllPharmacys();
//        System.out.println("---------------------2");
        mv.addObject("prescriptionsCount", dao.prescriptionPrintCount());
        mv.addObject("Pharmacys", dao1.getAllPharmacys());
//        System.out.println("---------------------3");

        return mv;
    }

    @RequestMapping(value = "/changePharmacy.html", method = RequestMethod.POST)
    public ModelAndView showEmployeeDetailsViewMethod(@RequestParam("id")int id)
    {
        try {
            infoLog.logActivities("in ShowAllEmployeeDetailsController-showEmployeeDetailsViewMethod: got "+id);
            System.out.println(id);
            Pharmacy r= dao1.show(id);
            ModelAndView mv= new ModelAndView();
            mv.setViewName("administrator/changePharmacyView");

            mv.addObject("pharmacy",dao1.show(id));
            return mv;
//            else
//            {
//                throw new Exception();
//            }
        }
        catch(Exception e)
        {
            infoLog.logActivities("in ShowAllEmployeeDetailsController-showEmployeeDetailsViewMethod: "+e);
            ModelAndView mv= new ModelAndView();
            mv.setViewName("failure");
            mv.addObject("error",e);
            return mv;
        }
    }
    @RequestMapping(value = "/submitPharmacy.html", method = RequestMethod.POST)
    public ModelAndView submitChangePharmacyMethod(@RequestParam("Having")int Having,@RequestParam("Name")String name)
    {
        try {
//            Name n1=new Name(firstName,middleName,lastName);
//            Address a1= new Address(residentialAddress, permanentAddress);
//            infoLog.logActivities("in EditEmployeeController-edit: got= "+eid+" "+n1+" "+birthdate+" "+gender+" "+email+" "+mobileNo+" "+adharNo+" "+country+" "+state+" "+city+" "+a1+" "+role+" "+qualification+" "+department);

            int res = dao1.edit(Having,name);
            infoLog.logActivities("returned to EditEmployeeController-edit: got= "+res);

            if(res==1)
            {
                ModelAndView mv= new ModelAndView();
                mv.setViewName("successPage");
                mv.addObject("prescriptionsCount", dao.prescriptionPrintCount());  //for receptionist only
                mv.addObject("Pharmacy", dao1.getAllPharmacys());


                return mv;
            }
            else
            {   throw new Exception();  }
        }
        catch(Exception e)
        {
            infoLog.logActivities("in EditEmployeeController-edit: "+e);
            ModelAndView mv= new ModelAndView();
            mv.setViewName("failure");
            mv.addObject("error","used is larger than the max");
            return mv;
        }
    }
    @RequestMapping("/PharmacyInfo.html")
    public ModelAndView Rview()
    {
//        System.out.println("---------------------0");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("receptionist/AllPharmacysView");
//        System.out.println("---------------------1");
//        dao1.getAllPharmacys();
//        System.out.println("---------------------2");
        mv.addObject("Pharmacys", dao1.getAllPharmacys());
        mv.addObject("prescriptionsCount", dao.prescriptionPrintCount());

//        System.out.println("---------------------3");

        return mv;
    }
    @RequestMapping("/usedPharmacy.html")
    public ModelAndView usedview(@RequestParam("name")String name)
    {
    try {

        ModelAndView mv= new ModelAndView();
        boolean b = dao1.MinusPharmacy(name);
        if (!b){throw new Exception();}
        mv.setViewName("receptionist/AllPharmacysView");
//        System.out.println("---------------------1");
//        dao1.getAllPharmacys();
//        System.out.println("---------------------2");
        mv.addObject("Pharmacys", dao1.getAllPharmacys());
        mv.addObject("prescriptionsCount", dao.prescriptionPrintCount());
        return mv;

//        System.out.println("---------------------3");
    }
    catch(Exception e)
    {
        infoLog.logActivities("in EditEmployeeController-edit: "+e);
        ModelAndView mv= new ModelAndView();
        mv.setViewName("failure");
        mv.addObject("error","Drug number is less then 0");
        return mv;
    }

    }

}
