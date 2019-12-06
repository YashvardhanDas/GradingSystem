package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("undergraduate")
public class UndergraduateStudent extends Student{
    public UndergraduateStudent() {
        super();
    }

    public UndergraduateStudent(String name, String surname, String buId, String email) {
        super(name,surname,buId,email);
    }
    public UndergraduateStudent(String name, String surname, String buId, String email,Course course) {
        super(name,surname,buId,email,course);
    }
}
