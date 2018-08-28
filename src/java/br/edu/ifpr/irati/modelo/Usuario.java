package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;

    @Column(name = "nomeCompleto", length = 100)
    private String nomeCompleto;

    @Column(name = "email", nullable = false, length = 25)
    private String email;

    @Column(name = "imagemPerfil", length = 100)
    private String imagemPerfil;

    @Column(name = "senhaAlfanumérica", nullable = false, length = 128)
    private String senhaAlfanumerica;

    @Column(name = "estadoUsuario", nullable = false, length = 10)
    private String estadoUsuario;

    public Usuario() {

        this.idUsuario = 0;
        this.nomeCompleto = "";
        this.email = "";
        this.imagemPerfil = "";
        this.senhaAlfanumerica = "";
    }

    public Usuario(int idUsuario, String nomeCompleto, String email, String imagemPerfil, String senhaAlfanumerica, String estadoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.imagemPerfil = imagemPerfil;
        this.senhaAlfanumerica = senhaAlfanumerica;
        this.estadoUsuario = estadoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getSenhaAlfanumerica() {
        return senhaAlfanumerica;
    }

    public void setSenhaAlfanumerica(String senhaAlfanumerica) {
        this.senhaAlfanumerica = senhaAlfanumerica;
    }

    @Override
    public boolean equals(Object obj) {
        Usuario u = (Usuario) obj;
        if (this.idUsuario == u.getIdUsuario()) {
            return true;
        } else {
            return false;
        }
    }
}
