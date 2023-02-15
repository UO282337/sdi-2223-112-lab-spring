package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorsController {
    @Autowired //Inyectar el servicio
    private ProfessorsService professorsService;

    @RequestMapping("/professor/list")
    public String getList() {
        return professorsService.getProfessors().toString();
    }
    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return "Professor added";
    }

    @RequestMapping("/professor/details/{dni}")
    public String getDetail(@PathVariable String dni) {
        return professorsService.getProfessor(dni).toString();
    }
    @RequestMapping("/professor/delete/{dni}")
    public String deleteProfessor(@PathVariable String dni) {
        professorsService.deleteProfessor(dni);
        return "Professor deleted";
    }

    @RequestMapping(value = "/professor/edit/{dni}", method=RequestMethod.POST)
    public String editProfessor(@ModelAttribute Professor professor) {
        professorsService.editProfessor(professor);
        return "Professor edited";
    }

}
