package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfessorsService {

    private List<Professor> profesoresList = new LinkedList<>();

    @PostConstruct
    public void init() {
        profesoresList.add(new Professor("11", "Miguel", "Fdez", "aaa"));
        profesoresList.add(new Professor("22", "Jaime", "Hdez", "bbb"));
    }

    public List<Professor> getProfessors() {
        return profesoresList;
    }

    public Professor getProfessor(String dni) {
        if (profesoresList.stream().filter(profesor->profesor.getDni().equals(dni)).findFirst().isPresent()) {
            return profesoresList.stream().filter(profesor->profesor.getDni().equals(dni)).findFirst().get();
        }
        return null;
    }

    public void addProfessor(Professor profesor){
        if(profesor.getDni() == null){
            profesor.setDni((profesoresList.get(profesoresList.size()-1).getDni()+1));
        }
        profesoresList.add(profesor);
    }
    public void deleteProfessor(String dni){
        profesoresList.removeIf(profesor -> profesor.getDni().equals(dni));
    }
}
