package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("graduate")
public class GraduateStudent extends Student{
    public GraduateStudent() {
        super();
    }

    public GraduateStudent(String name, String surname, String buId, String email) {
        super(name,surname,buId,email);
    }
    public GraduateStudent(String name, String surname, String buId, String email,Course course) {
        super(name,surname,buId,email,course);
    }
}
