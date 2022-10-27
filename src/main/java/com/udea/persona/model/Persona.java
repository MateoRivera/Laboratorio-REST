package com.udea.persona.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idperson")
    private Long idPerson;

    @Column(name = "firstname", nullable = false, length = 80)
    private @NonNull String firstName;

    @Column(name = "lastname", nullable = false, length = 80)
    private @NonNull String lastName;

    @Column(name = "email", nullable = false, length = 80)
    private @NonNull String email;

    @Column(name = "mobilePhoneNumber", nullable = false, length = 80)
    private @NonNull String mobilePhoneNumber;

    @Column(name = "salary", nullable = false)
    private float salary;

    @Column(name = "position", nullable = false, length = 80)
    private @NonNull String position;

    @Column(name = "address", nullable = false, length = 80)
    private @NonNull String address;

    @Column(name = "office", nullable = false, length = 80)
    private @NonNull String office;

    @Column(name = "dependency", nullable = false, length = 80)
    private @NonNull String dependency;

    @Column(name = "dateAdmission", nullable = false)
    private @NonNull Date dateAdmission;

    @ColumnDefault("false")
    @Column(name = "salaryUpdated", nullable = false)
    private boolean salaryUpdated;
}
