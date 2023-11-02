package tn.esprit.spring.kaddem.dto;

import java.util.Set;

public class DepartementDTO {
    private Integer idDepart;
    private String nomDepart;
    private Set<Integer> etudiantIds; // Use IDs to reference associated Etudiants

    public DepartementDTO() {
        // This constructor is intentionally left empty. The object can be initialized using other constructors.
    }

    // Getters and setters for all fields

    public Integer getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Integer idDepart) {
        this.idDepart = idDepart;
    }

    public String getNomDepart() {
        return nomDepart;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }

    public Set<Integer> getEtudiantIds() {
        return etudiantIds;
    }

    public void setEtudiantIds(Set<Integer> etudiantIds) {
        this.etudiantIds = etudiantIds;
    }
}
