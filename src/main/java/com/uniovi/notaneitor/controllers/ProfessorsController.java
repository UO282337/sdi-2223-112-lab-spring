package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.services.ProfessorsService;
import com.uniovi.notaneitor.validators.ProfessorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorsController {
    @Autowired
    private ProfessorsService professorService;
    @Autowired
    private ProfessorValidator professorFormValidator;

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String addProfessor(@Validated Professor professor, BindingResult result) {
        professorFormValidator.validate(professor, result);
        if (result.hasErrors()){
            return "professor/add";
        }
        professorService.addProfessor(professor);
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/add")
    public String getProfessor(Model model){
        model.addAttribute("professor",new Professor());
        return "professor/add";
    }

    @RequestMapping("/professor/details/{dni}")
    public String viewDetails(Model model, @PathVariable String dni){
        model.addAttribute("professor",professorService.getProfessor(dni));
        return "professor/details";
    }

    @RequestMapping("/professor/delete/{dni}")
    public String deleteProfesor(@PathVariable String dni){
        professorService.deleteProfessor(dni);
        return "redirect:/professor/list";
    }

    @RequestMapping("/professor/list")
    public String viewProfessors(Model model){
        model.addAttribute("professors",professorService.getProfessors());
        return "professor/list";
    }
}
