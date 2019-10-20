package com.app.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.User;
import com.app.service.IUserService;
import com.app.util.EmailUtil;
import com.app.util.UserUtil;
import com.app.validator.UserValidator;
import com.app.view.UserExcelView;
import com.app.view.UserPdfView;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private UserValidator validator;  
	@Autowired
	private UserUtil userUtil;
	@Autowired
	private EmailUtil emailUtil;

	//1.show register page
	@RequestMapping("/register")
	public String showRegisterPage(ModelMap map) {

		map.addAttribute("user", new User());
		return "UserRegisterPage";
	}

	//2.insert user details in Db
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String saveUserDetails(@ModelAttribute User user,Errors errors,ModelMap map) {

		validator.validate(user, errors);

		if (errors.hasErrors()) {
			map.addAttribute("message", "please check all fields !!");
		} else {
			String pwd=user.getUserPassword();
			Integer userId=service.saveUser(user);
			String message=user.getUserName()+" saved with Id :"+userId;
			map.addAttribute("user", new User());
			
			//email config start
			String text="Hello! " + user.getUserName()
			+ " , welcome to Warehouse App.. Your user name is: " + user.getUserEmail()
			+ " , Password is : " + pwd
			+ " , Your Roles are : " +user.getUserRoles();
			
			boolean flag=emailUtil.sendEmail(user.getUserEmail(), "Welcome to App User!!", text);
			if(flag)
				message+=", Email also sent!!";
			else
				message+=", Email sending is failed!!";
			//email config end
			
			map.addAttribute("message", message);
		}

		return "UserRegisterPage";
	}

	/*//3.view all users
	@RequestMapping("/viewAll")
	public String viewAllUsers(ModelMap map) {

		map.addAttribute("user", service.getAllUsers());

		return "UserData";
	}*/

	//4.delete one user base on userId
	@RequestMapping("/delete")
	public String deleteOneUser(@RequestParam Integer userId,ModelMap map) {

		try {
			//delete one row
			service.deleteUser(userId);
			map.addAttribute("message", "One User with Id "+userId+" Successfully deleted");
		} catch (Exception e) {
			map.addAttribute("message", "No details found with Id : "+userId);
		}

		//load all users from DB and send to UI
		map.addAttribute("user", service.getAllUsers());
		return "UserData";

	}


	//5.view one user da=etails
	@RequestMapping("/view")
	public String viewOne(@RequestParam(required=false,defaultValue="0") Integer userId,ModelMap map) {

		String page=null;
		if (userId!=0) {
			// load one user data and send to UI
			map.addAttribute("user", service.getUserById(userId));
			page = "UserView";

		} else {
			// load one user data and send to UI
			map.addAttribute("user", service.getAllUsers());
			page = "UserData";
		}

		return page;
	}

	//6.show edit page
	@RequestMapping("/editPage")
	public String showEditPage(@RequestParam Integer userId,ModelMap map) {

		map.addAttribute("user", service.getUserById(userId));

		return"UserEdit";
	}


	//7.update user details
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateUserDetails(@ModelAttribute User user,ModelMap map) {

		//update user inDb
		service.updateUser(user);
		map.addAttribute("message", user.getUserName()+" is Successfully Updated");

		//load all users and send to UI
		map.addAttribute("user", service.getAllUsers());

		return "UserData";
	}

	/*//8.export all rows to excel
	@RequestMapping("/excelExport")
	public ModelAndView exportAllUserToExcel() {

		return new ModelAndView(new UserExcelView(),"user",service.getAllUsers());
	}*/

	//8.export excel
	@RequestMapping("/excelExport")
	public ModelAndView exportOneUserToExcel(@RequestParam(required=false,defaultValue="0") Integer userId) {

		if (userId!=0) {
			return new ModelAndView(new UserExcelView(),"user",Arrays.asList(service.getUserById(userId)));

		} else {
			return new ModelAndView(new UserExcelView(),"user",service.getAllUsers());

		}
		//return new ModelAndView(new UserExcelView(),"user",Arrays.asList(service.getUserById(userId)));
	}

	/*//10.export all rows to pdf
	@RequestMapping("/pdfExport")
	public ModelAndView exportAllUserToPdf() {

		return new ModelAndView(new UserPdfView(),"user",service.getAllUsers());
	}*/

	//9.export pdf
	@RequestMapping("/pdfExport")
	public ModelAndView exportOneUserTopdf(@RequestParam(required=false,defaultValue="0") Integer userId) {

		if (userId!=0) {

			return new ModelAndView(new UserPdfView(),"user",Arrays.asList(service.getUserById(userId)));
		} else {

			return new ModelAndView(new UserPdfView(),"user",service.getAllUsers());
		}
	}

	//10.generate chart
	@RequestMapping("/report")
	public String generateChart() {

		String path = servletContext.getRealPath("/");
		List<Object[]> users=service.getUsersCount();
		userUtil.generatePieChart(path, users);
		userUtil.generateBarChart(path, users);
		return "UserReport";
	}

}
