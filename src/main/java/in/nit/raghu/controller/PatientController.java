package in.nit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import in.nit.raghu.entity.Patient;

import in.nit.raghu.exception.PatientNotFoundException;
import in.nit.raghu.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService service;
	
	//1. show Register page
		@GetMapping("/register")
		public String registerPatient(Model model) {
			model.addAttribute("patient",new Patient());
			return "PatientRegister";
		}
		
		//2 save on submit
		@PostMapping("/save")
		public String savePatient(@ModelAttribute Patient patient, Model model) {
			java.lang.Long id=service.savePatient(patient);
			model.addAttribute("message","Patient created with Id:"+id);
			model.addAttribute("patient",new Patient()) ;
			return "PatientRegister";
		}
        
		//3 display all patients
		@GetMapping("/all")
		public String getAllPatients(Model model,
				@RequestParam(value = "message", required = false) String message) {
			List<Patient> list=service.getAllPatients();
			model.addAttribute("list",list);
			model.addAttribute("message",message);
			return "PatientData";
		}

		 //4 delete by id
		@GetMapping("/delete")
		public String deletePatient(@RequestParam Long id, RedirectAttributes attributes) {
			try {
				service.deletePatient(id);
				attributes.addAttribute("message","Patient deleted with Id:"+id);
			} catch(PatientNotFoundException e) {
				e.printStackTrace() ;
				attributes.addAttribute("message",e.getMessage());
			}
			return "redirect:all";
		}
        
		//5 edit patient
		@GetMapping("/edit")
		public String editPatient(@RequestParam Long id, Model model, RedirectAttributes attributes) {
			String page=null;
			try {
				Patient ob=service.getOnePatient(id);
				model.addAttribute("patient",ob);
				page="PatientEdit";
			} catch(PatientNotFoundException e) {
				e.printStackTrace() ;
				attributes.addAttribute("message",e.getMessage());
				page="redirect:all";
			}
			return page;
		}
        
		//6 update patient 
		@PostMapping("/update")
		public String updatePatient(@ModelAttribute Patient patient, RedirectAttributes attributes) {
			service.updatePatient(patient);
			attributes.addAttribute("message","Patient updated");
			return "redirect:all";
		}
}
