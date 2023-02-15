package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorsController {
    @Autowired //Inyectar el servicio
    private ProfessorsService professorsService;

    @RequestMapping("/professor/list")
    public String getList(Model model) {
        model.addAttribute("professors", professorsService.getProfessors());
        return "professor/list";
    }
    @RequestMapping(value="/professor/add")
    public String getProfessor() {
        return "professor/add";
    }

    @RequestMapping(value = "/professor/add", method=RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return "redirect:/professor/list";
    }

    @RequestMapping("/professor/details/{dni}")
    public String getDetail(Model model, @PathVariable Long dni) {
        model.addAttribute("professor", professorsService.getProfessor(dni));
        return "professor/details";
    }

    @RequestMapping("/professor/delete/{dni}" )
    public String deleteProfessor(@PathVariable Long dni) {
        professorsService.deleteProfessor(dni);
        return "redirect:/professor/list";
    }

}
