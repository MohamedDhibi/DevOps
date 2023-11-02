package tn.esprit.spring.kaddem.dto;



public abstract class BaseDTO {
    private Integer id;
    // Common fields that are present in both DepartementDTO and ContratDTO

    protected BaseDTO() {
        // This constructor is intentionally left empty. The object can be initialized using other constructors.
    }

    // Getters and setters for the common fields
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Other common methods can be added here if necessary
}
