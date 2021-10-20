package in.nit.raghu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.raghu.entity.Appointment;
import in.nit.raghu.entity.Doctor;
import in.nit.raghu.exception.AppointmentNotFoundException;
import in.nit.raghu.service.AppointmentService;
import in.nit.raghu.service.DoctorService;
import in.nit.raghu.service.SpecializationService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
   
	@Autowired
	private AppointmentService service;
	
	//for doctor module integration
	@Autowired
	private DoctorService doctorservice;
	
	//for slot view integration
	@Autowired
	private SpecializationService specializationService;
	
	private void createCommonUi(Model model) {
		model.addAttribute("doctors",doctorservice.getDoctorIdAndNames());
	}
	
	//register page
	@GetMapping("/register")
	public String registerApppointment(Model model) {
		model.addAttribute("appointment",new Appointment());
		createCommonUi(model);
		return "AppointmentRegister";	
	}
	
	//save page
	@PostMapping("/save")
	public String saveAppointment(Model model,@ModelAttribute Appointment appointment) {
		java.lang.Long id=service.saveAppointment(appointment);
		model.addAttribute("message","Appointment created with ID: "+id);
		model.addAttribute("Appointment",new Appointment());
		createCommonUi(model);
		return "AppointmentRegister";
	}
	
	//show all appointments
	@GetMapping("/all")
	public String getAllAppointments(Model model,
			@RequestParam(value = "message", required = false) String message) {
		List<Appointment> list=service.getAllAppointments();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "AppointmentData";
	}
	
	//delete by id
	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam Long id, RedirectAttributes  attributes ) {
		try {
			service.deleteAppointment(id);
			attributes.addAttribute("message","Appointment deleted with ID:"+id);
		}catch(AppointmentNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
		
	}
	
	  //edit page by id
	  @GetMapping("/edit")
	  public String editApppointment(@RequestParam Long id, Model model,
			  RedirectAttributes attributes) {
		  String page=null;
		  try {
			  Appointment ob=service.getOneAppointment(id);
			  model.addAttribute("appointment",ob);
			  createCommonUi(model);
			  page="AppointmentEdit";
		  }catch(AppointmentNotFoundException e) {
			  e.printStackTrace();
			  model.addAttribute("message",e.getMessage());
			  page="redirect:all";
		  }
		  return page;
	  }
	  
	  //update page
	  @PostMapping("/update")
		  public String updateAppointment(@ModelAttribute Appointment appointment,
				  RedirectAttributes attributes) {
		   service.updateAppointment(appointment);
		   attributes.addAttribute("message", "Appointment updated");
		   return "redirect:all";
	  }
	  
	//.. view appointments page..
		@GetMapping("/view")
		public String viewSlots(
				@RequestParam(required = false, defaultValue = "0") Long specId,
				Model model
				) 
		{
			//fetch data for Spec DropDown
			Map<Long,String> specMap =  specializationService.getSpecIdAndName();
			model.addAttribute("specializations", specMap);

			List<Doctor> docList =  null;
			String message = null;
			if(specId<=0) { //if they did not select any spec
				 docList = doctorservice.getAllDoctors();
				 message = "Result : All Doctors";
			} else {
				 docList = doctorservice.findDoctorBySpecName(specId);
				 message = "Result : "+specializationService.getOneSpecialization(specId).getSpecName()+" Doctors";
			}
			model.addAttribute("docList", docList);

			model.addAttribute("message", message);

			return "AppointmentSearch";
		}

		//.. book result...
		//.. view slots...
		@GetMapping("/viewSlot")
		public String showSlots(
				@RequestParam Long id,
				Model model
				) 
		{
			//fetch apps based on doctor id
			List<Object[]> list = service.getAppoinmentsByDoctor(id);
			model.addAttribute("list", list);
			Doctor doc = doctorservice.getOneDoctor(id);
			model.addAttribute("message", "RESULTS SHOWING FOR : " + doc.getFirstName()+" "+doc.getLastName());
			return "AppointmentSlots";
		}

	  
 }
	


