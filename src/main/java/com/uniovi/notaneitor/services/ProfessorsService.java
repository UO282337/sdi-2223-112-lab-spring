package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {
    private List<Professor> professors = new ArrayList<>();

    @PostConstruct
    public void init() {
        professors.add(new Professor(1L, "Miguel", "Gonzalez Navarro", "Software"));
        professors.add(new Professor(2L, "Javier", "Gonzalez Velázquez", "Software"));
        professors.add(new Professor(3L, "Richard", "Marqués Garay", "Software"));
    }
    public List<Professor> getProfessors() {
        return professors;
    }
    public Professor getProfessor(Long dni) {
        return professors.stream()
                .filter(professor -> professor.getDni().equals(dni)).findFirst().get();
    }
    public void addProfessor(Professor professor) {
        professors.add(professor);
    }
    public void deleteProfessor(Long dni) {
        professors.removeIf(professor -> professor.getDni().equals(dni));
    }

}
