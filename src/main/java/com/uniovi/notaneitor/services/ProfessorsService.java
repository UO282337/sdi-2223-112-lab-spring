package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {
    @Autowired
    private ProfessorsRepository professors;

    public List<Professor> getProfessors() {
        List<Professor> professors_ = new ArrayList<Professor>();
        professors.findAll().forEach(professors_::add);
        return professors_;
    }

    public Professor getProfessor(Long dni) {
        return professors.findById(dni).get();
    }

    public void addProfessor(Professor professor) {
        professors.save(professor);
    }
    public void deleteProfessor(Long dni) {
        professors.deleteById(dni);
    }
}
