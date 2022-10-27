package com.udea.persona.controller;

import com.udea.persona.exception.ModelNotFoundException;
import com.udea.persona.model.Persona;
import com.udea.persona.service.PersonaService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/persona")
@CrossOrigin("*")

public class PersonaController {

    @Autowired
    PersonaService personService;

    @PostMapping("/save")
    public long save(
            @RequestBody Persona person) {

        personService.save(person);
        return person.getIdPerson();
    }

    @GetMapping("/listAll")
    public Iterable<Persona> listAllPersons() {
        return personService.list();
    }

    @GetMapping("/list/{id}")
    public Persona listPersonById(
            @PathVariable("id") Long id) {
        Optional<Persona> person = personService.listId(id);
        if (person.isPresent()) {
            return person.get();
        }

        throw new ModelNotFoundException("ID de persona invalido");
    }

    @PutMapping("/update/{id}")
    public Persona updatePerson(
            @PathVariable("id") Long id,
            @RequestBody Persona person) {
        Optional<Persona> personOptional = personService.listId(id);
        if (personOptional.isPresent()) {
            Persona personUpdate = personOptional.get();
            personUpdate.setFirstName(person.getFirstName());
            personUpdate.setLastName(person.getLastName());
            personUpdate.setEmail(person.getEmail());
            personUpdate.setMobilePhoneNumber(person.getMobilePhoneNumber());
            personUpdate.setSalary(person.getSalary());
            personUpdate.setPosition(person.getPosition());
            personUpdate.setAddress(person.getAddress());
            personUpdate.setOffice(person.getOffice());
            personUpdate.setDependency(person.getDependency());
            personUpdate.setDateAdmission(person.getDateAdmission());
            personUpdate.setSalaryUpdated(person.isSalaryUpdated());
            personService.save(personUpdate);
            return personUpdate;
        }

        throw new ModelNotFoundException("ID de persona invalido");
    }

    @DeleteMapping("/delete/{id}")
    public Long deletePerson(
            @PathVariable("id") Long id) {
        Optional<Persona> person = personService.listId(id);
        if (person.isPresent()) {
            personService.delete(id);
        } else {
            throw new ModelNotFoundException("ID de persona invalido");
        }
        return id;
    }

    @PutMapping("/updateSalary/{id}")
    public boolean updateSalary(
            @PathVariable("id") Long id) {

        Optional<Persona> personOptional = personService.listId(id);
        // Contemos cuántos días han pasado desde la última actualización del salario
        Date today = new Date(System.currentTimeMillis());
        long difference = ChronoUnit.DAYS.between(LocalDate.parse(personOptional.get().getDateAdmission().toString()),
                LocalDate.parse(today.toString()));
        System.out.println("=========Días de diferencia: "
                + String.valueOf(difference) + "=========");
        if (personOptional.isPresent() && difference >= 365 * 2 && !personOptional.get().isSalaryUpdated()) {
            Persona personUpdate = personOptional.get();
            personUpdate.setSalary((float) (personUpdate.getSalary() * 1.1));
            personUpdate.setSalaryUpdated(true);
            personService.save(personUpdate);
            return true;
        }
        return false;

        // throw new ModelNotFoundException("ID de persona invalido");
    }
}
