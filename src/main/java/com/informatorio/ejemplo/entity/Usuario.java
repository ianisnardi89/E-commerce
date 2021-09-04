package com.informatorio.ejemplo.entity;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.informatorio.ejemplo.service.UsuarioService;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", })
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = true)
    @NotBlank(message = "Debe ingresar un nombre")
    @Size(min = 2, message = "El nombre debe teber dos caracteres como minimo")
    private String nombre;

    @Column(nullable = false, updatable = true)
    @NotBlank(message = "Debe ingresar un nombre")
    @Size(min = 2, message = "El nombre debe teber dos caracteres como minimo")
    private String apellido;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion = UsuarioService.creacion();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    @JsonIgnoreProperties("usuario")
    private Direccion direccion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("usuario")
    private List<Carrito> carritos = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Rol rol;

    @Column(nullable = false, unique = true)
    @Email()
    private String email;

    @Column(nullable = false)
    private String pass;

    

    //NOMBRE
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    //APELLIDO
    public String getApellido(){return apellido;}
    public void setApellido(String apellido){this.apellido = apellido;}

    //DIRECCION
    public Direccion getDireccion(){return direccion;}
    public void setDireccion(Direccion direccion){this.direccion = direccion;}

    //CARRITOS
    public List<Carrito> getCarritos(){return carritos;}

    //ID
    public Long getId(){return id;}

    //FECHA DE CREACION
    public Date getFechaDeCreacion(){return fechaDeCreacion;}

    //ROL
    public Rol getRol(){return rol;}
    public void setRol(Rol rol){this.rol = rol;}

    //EMAIL
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    //PASSWORD
    
    
    public String pass(){return pass;}

    public void setPass(String pass){this.pass = pass;}




















}
