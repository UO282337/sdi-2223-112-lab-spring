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
        professors.add(new Professor("1", "Miguel", "Gonzalez Navarro", "Software"));
        professors.add(new Professor("2", "Javier", "Gonzalez Velázquez", "Software"));
        professors.add(new Professor("3", "Richard", "Marqués Garay", "Software"));
        professors.add(new Professor("4", "Alex", "García Fernández", "Software"));
    }
    public List<Professor> getProfessors() {
        return professors;
    }
    public Professor getProfessor(String dni) {
        return professors.stream()
                .filter(professor -> professor.getDni().equals(dni)).findFirst().get();
    }
    public void addProfessor(Professor professor) {
        if (professor.getDni() == null) {
            professor.setDni("");
        }
        professors.add(professor);
    }
    public void deleteProfessor(String dni) {
        professors.removeIf(professor -> professor.getDni().equals(dni));
    }

    public void editProfessor(Professor professor) {
        if (this.getProfessor(professor.getDni()) != null) {
            this.deleteProfessor(professor.getDni()); // eliminamos el profesor
            this.addProfessor(professor); // añadimos este profesor con los nuevos datos
        }
    }
}
