package com.project.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
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

public class RoomController {
    SessionFactory sf;//hibernate configuration in springMVC-servlet.xml file

    @Autowired
    LoginDao infoLog;
}
