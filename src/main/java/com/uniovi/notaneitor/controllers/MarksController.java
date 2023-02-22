package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.services.MarksService;
import com.uniovi.notaneitor.services.UsersService;
import com.uniovi.notaneitor.validators.MarkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MarksController {
    @Autowired //Inyectar el servicio
    private MarksService marksService;

    // Inyectamos el servicio
    @Autowired
    private UsersService usersService;

    @Autowired
    private MarkValidator markValidator;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/mark/list")
    public String getList(Model model) {
        Set<Mark> consultedList= (Set<Mark>) httpSession.getAttribute("consultedList");
        if ( consultedList == null ) {
            consultedList = new HashSet<Mark>();
        }
        model.addAttribute("consultedList", consultedList);
        model.addAttribute("markList", marksService.getMarks());
        return "mark/list";
    }

    //@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    //public String setMark(@RequestParam String description, @RequestParam String score) {
    //    return "Added: " + description + " with score: " + score;
    //}


    //@RequestMapping("/mark/details")
    //public String getDetail(@RequestParam Long id) {
    //    return "Getting Details =>" + id;
    //}

    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }
    @RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
    public String setEdit(Model model, @Validated Mark mark, @PathVariable Long id, BindingResult result){
        markValidator.validate(mark,result);
        if(result.hasErrors()) {
            model.addAttribute("usersList", usersService.getUsers());
            return "mark/edit";
        }
        Mark originalMark = marksService.getMark(id);
        // modificar solo score y description
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/" + id;
    }

    @RequestMapping("/mark/list/update")
    public String updateList(Model model){
        model.addAttribute("markList", marksService.getMarks() );
        return "mark/list :: tableMarks";
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(Model model, @Validated Mark mark, BindingResult result) {
        markValidator.validate(mark, result);
        if(result.hasErrors()) {
            model.addAttribute("usersList", usersService.getUsers());
            return "/mark/add";
        }
        marksService.addMark(mark);
        return "redirect:/mark/list";
    }
    @RequestMapping(value = "/mark/add")
    public String getMark(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        model.addAttribute("mark", new Mark());
        return "mark/add";
    }

}
