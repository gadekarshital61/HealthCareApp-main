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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.raghu.entity.Specialization;
import in.nit.raghu.service.SpecializationService;
import in.nit.raghu.view.SpecializationExcelView;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private SpecializationService service;
	
	/*
	 * 1 show register page
	 */	
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}
	
	/**
	 * 2. On Submit Form save data
	 */
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization, Model model)
	{
		Long id = service.saveSpecialization(specialization);
		String message ="Record ("+id+") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	/* 
	 * 3 get all list
	 */
	
	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message",required = false) String message){
		
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list",list);
		model.addAttribute("message" ,message);
		return "SpecializationData";
	}
	/*
	 * 4 delete by id
	 */
	@GetMapping("/delete")
	public String deleteData(Model model,@RequestParam Long id,RedirectAttributes attributes) 
	{
		service.removeSpecialization(id);
		attributes.addAttribute("message", "Record ("+id+") is removed");
		return "redirect:all";
	}
	
	
	/**
	 * 5. Fetch Data into Edit page
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model) 
	{
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("specialization", spec);
		return "SpecializationEdit";
	}

	
	/***
	 * 6. Update Form data and redirect to all
	 */
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization,RedirectAttributes attributes)
	{
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Record ("+specialization.getId()+") is updated");
		return "redirect:all";
	}

	/*
	 *7 Ajax validation
	 *
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code) 
	{
		String message = "";
		if(service.isSpecCodeExist(code)) {
			message = code + " already exist";
		}
		return message; //this is not viewName(it is message)
	}
	*/
	// 9  AJAX  bug fixing for edit 
	
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(
			@RequestParam String code,
			@RequestParam Long id) 
	{
		String message = "";
		if(service.isSpecCodeExist(code)) {
		if(id==0 && service.isSpecCodeExist(code)) { //register check
			message = code + ", already exist";
		} else if(id!=0 && service.isSpecCodeExistForEdit(code,id)) { //edit check
			message = code + ", already exist";
		} 
   }
		return message;
 }
	
// AJAX bug fixing for edit
	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String name,@RequestParam Long id) {
		String message=" ";
		if (service.isSpecNameExist(name)) {
			if(id==0 && service.isSpecNameExist(name)) {
				message=name+" already exist";
			}else if (id!=0 && service.isSpecNameExistForEdit(name, id)) {
				message =name+ "  already exist";
			}
		}
		return message;
			
	}


	/* 
	 * 8 Excelsheetview
	 *  */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m =  new ModelAndView();
		m.setView(new SpecializationExcelView());
		
		//read data from DB
		List<Specialization> list = service.getAllSpecializations();
		//send to Excel Impl class
		m.addObject("list", list);
		
		return m;
	}
	
	
}
	
	

