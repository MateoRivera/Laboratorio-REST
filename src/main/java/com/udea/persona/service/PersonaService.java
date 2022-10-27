package com.udea.persona.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.persona.dao.IPersonaDAO;
import com.udea.persona.model.Persona;

@Service
public class PersonaService {
    @Autowired
    private IPersonaDAO dao;

    public Persona save(Persona t) {
        return dao.save(t);
    }

    public Persona update(Persona t) {
        return dao.save(t);
    }

    // public void delete(Persona t) {
    public void delete(Long id) {
        dao.deleteById(id);
    }

    public Iterable<Persona> list() {
        return dao.findAll();
    }

    public Optional<Persona> listId(Long id) {
        return dao.findById(id);
    }

}
