package br.edu.ifpr.irati.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "diretorEnsino")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class DiretorEnsino extends Usuario implements Serializable {

    @OneToMany(mappedBy = "diretorEnsino")
    private List<Curso> cursos;
    
    @OneToMany(mappedBy = "diretorEnsino")
    private List<PTDSubmetido> PTDsSubmetidos;
        
    public DiretorEnsino() {
        super();
        
    }

    public DiretorEnsino(int idUsuario, String nomeCompleto, String email, String imagemPerfil, String senhaAlfanumerica) {
        super(idUsuario, nomeCompleto, email, imagemPerfil, senhaAlfanumerica);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<PTDSubmetido> getPTDsSubmetidos() {
        return PTDsSubmetidos;
    }

    public void setPTDsSubmetidos(List<PTDSubmetido> PTDsSubmetidos) {
        this.PTDsSubmetidos = PTDsSubmetidos;
    }
}
