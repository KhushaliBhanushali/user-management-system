package com.springboot.loginsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springboot.loginsystem.entity.User;
import com.springboot.loginsystem.service.UserService;
import com.springboot.loginsystem.util.Helper;

import jakarta.servlet.http.HttpSession;
import static com.springboot.loginsystem.constant.JWTUtil.EMAIL_EXIST;
import static com.springboot.loginsystem.constant.JWTUtil.ROLE;
import static com.springboot.loginsystem.constant.JWTUtil.REGISTER_SUCCESS;

import java.util.Date;

@Controller
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/register")
	public String register() {
		return "registration";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, HttpSession session) {
		
		if(userService.checkEmailExist(user.getEmail())) {
			session.setAttribute("msg", EMAIL_EXIST);
		}else {
			user.setRole(ROLE);
			user.setRegDate(new Date());
			user.setVcode("1234");
			
			session.setAttribute("msg", REGISTER_SUCCESS);
			userService.createOrUpdateUser(user);
		}
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String checkLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		
		User user = userService.getUserByEmailandPassword(email, password);
		
		if(user != null) {
			session.setAttribute("uname", user.getFirstName() + " " + user.getLastName());
			session.setAttribute("uid", user.getId());
			session.setAttribute("urole", user.getRole());
			
			System.out.println(user);
			
			if(Helper.checkUserRole()) {
				session.setAttribute("msg", "You are successfully login...");
				return "/dashboard";
			}else {
				session.setAttribute("msg", "Something went wrong..");
				return "redirect:/logout";
			}
		}else {
			session.setAttribute("msg", "Wrong username or password");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attributes.getRequest().getSession();
		
		if(session.getAttribute("uname") != null)
			session.removeAttribute("uname");
		
		if (session.getAttribute("uid") != null) 
			session.removeAttribute("uid");
		
		if(session.getAttribute("urole") != null)
			session.removeAttribute("urole");
		
		session.setAttribute("msg", "You are successfully logout from system..");
		return "redirect:/";
	}
	
	@GetMapping("/profile")
	public String profile(Model m) {
		
		if(!Helper.checkUserRole()) {
			return "redirect:/logout";
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
		} 
		User user = userService.loadUserById(uid);
		m.addAttribute("user",user);
		
		return "user_profile";
	}
	
	@GetMapping("/edit-profile")
	public String editProfile(Model m) {
		
		if(!Helper.checkUserRole()) {
			return "redirect:/logout";
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
		} 
		User user = userService.loadUserById(uid);
		m.addAttribute("user",user);
		
		return "edit_profile";
	}
	
	@PostMapping("/edit-profile")
	public String updateProfile(User user) {
		if(!Helper.checkUserRole()) {
			return "redirect:/logout";
		}
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
			User user2 = userService.loadUserById(uid);
			user2.setFirstName(user.getFirstName());
			user2.setLastName(user.getLastName());
			user2.setEmail(user.getEmail());
			user2.setPhone(user.getPhone());
			userService.createOrUpdateUser(user2);
		} 
		
		session.setAttribute("msg", "Profile Updated successfully..");
		
		return "redirect:/profile";
	}
	
	@GetMapping("/change-password")
	public String changepassword1(@RequestParam(required=false, name="password")String password) {
		if(!Helper.checkUserRole()) {
			return "redirect:/logout";
		}
		return "change_password";
	}
	
	@PostMapping("/change-password")
	public String changepassword2(@RequestParam(required=false, name="password")String password) {
		if(!Helper.checkUserRole()) {
			return "redirect:/logout";
		}
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int uid = 0;
		if(session.getAttribute("uid") != null) {
			uid = (int)session.getAttribute("uid");
			User user2 = userService.loadUserById(uid);
			user2.setPassword(password);
			userService.createOrUpdateUser(user2);
		} 
		
		session.setAttribute("msg", "Password change successfully..");
		
		return "redirect:/profile";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "forgot_password";
	}
	
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam("email") String email, Model m) {
		
		if(userService.checkEmailExist(email)) {
				User user = userService.getUserByEmail(email);
				m.addAttribute("uid", user.getId());
				return "/reset-password";
			}
		else {
			return "forgot_password";
		}
	}
	
	@GetMapping("/reset-password")
	public String resetPassword() {
		return "reset_password";
	}
	
	@PostMapping("/reset-password")
	public String resetPassword(@RequestParam ("id") int id, @RequestParam("password") String password, @RequestParam("con_password") String con_password) {
		if(password.equals(con_password)) {
			User user = userService.loadUserById(id);
			user.setPassword(password);
			userService.createOrUpdateUser(user);
		}else {
			
		}
		return "redirect:/";
	}
}
