package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("phd")
public class PhdStudent extends Student{
    public PhdStudent() {
        super();
    }

    public PhdStudent(String name, String surname, String buId, String email) {
        super(name,surname,buId,email);

    }
}
