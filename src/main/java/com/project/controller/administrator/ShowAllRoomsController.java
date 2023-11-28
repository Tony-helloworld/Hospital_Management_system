package com.project.controller.administrator;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.RoomDao;
import com.project.dao.receptionist.PatientPrescriptionDao;
import com.project.entity.Address;
import com.project.entity.Name;
import com.project.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.LoginDao;
import com.project.dao.administrator.AllEmployeeDetailsDao;
import com.project.dao.administrator.EmployeeDetailsDao;
import com.project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.LoginDao;
import com.project.dao.administrator.AllEmployeeDetailsDao;
import com.project.dao.administrator.EmployeeDetailsDao;
import com.project.entity.Employee;
@Controller
public class ShowAllRoomsController {
    @Autowired
    RoomDao dao1;
    @Autowired
    EmployeeDetailsDao dao2;
    @Autowired
    PatientPrescriptionDao dao;
    @Autowired
    LoginDao infoLog;

    @RequestMapping("/allRoomsView.html")
    public ModelAndView view()
    {
//        System.out.println("---------------------0");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("administrator/AllRoomsView");
//        System.out.println("---------------------1");
        dao1.getAllRooms();
//        System.out.println("---------------------2");
        mv.addObject("Rooms", dao1.getAllRooms());
//        System.out.println("---------------------3");

        return mv;
    }

    @RequestMapping(value = "/changeRoom.html", method = RequestMethod.POST)
    public ModelAndView showEmployeeDetailsViewMethod(@RequestParam("id")long id)
    {
        try {
            infoLog.logActivities("in ShowAllEmployeeDetailsController-showEmployeeDetailsViewMethod: got "+id);
            System.out.println(id);
            Room r= dao1.show(id);
            ModelAndView mv= new ModelAndView();
            mv.setViewName("administrator/changeRoomView");

            mv.addObject("room",dao1.show(id));
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
    @RequestMapping(value = "/submitRoom.html", method = RequestMethod.POST)
    public ModelAndView submitChangeRoomMethod(@RequestParam("Used")int Used, @RequestParam("Max")int Max,@RequestParam("Name")String name)
    {
        try {
//            Name n1=new Name(firstName,middleName,lastName);
//            Address a1= new Address(residentialAddress, permanentAddress);
//            infoLog.logActivities("in EditEmployeeController-edit: got= "+eid+" "+n1+" "+birthdate+" "+gender+" "+email+" "+mobileNo+" "+adharNo+" "+country+" "+state+" "+city+" "+a1+" "+role+" "+qualification+" "+department);

            int res = dao1.edit(Used,Max,name);
            infoLog.logActivities("returned to EditEmployeeController-edit: got= "+res);

            if(res==1)
            {
                ModelAndView mv= new ModelAndView();
                mv.setViewName("successPage");
                mv.addObject("prescriptionsCount", dao.prescriptionPrintCount());  //for receptionist only
                mv.addObject("Rooms", dao1.getAllRooms());


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
}
