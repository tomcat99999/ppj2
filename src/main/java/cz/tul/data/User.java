package cz.tul.data;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by The CAT
 * */


@Entity
@Table(name = "users")
public class User {
    @Id
    @org.springframework.data.annotation.Id
    @Column(name="id")
    // @Type(type="text")
    private int id;

    @Column(name = "name", unique=true)
    private String name;

    @Column(name = "registration")
    private Date registrationDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Image> imageSet = new HashSet<>();

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.registrationDate = new Date();
    }

    public User(int id, String name, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != 0 ? !(id==user.id) : user.id != 0) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return !(registrationDate != null ? !registrationDate.equals(user.registrationDate) : user.registrationDate != null);

    }
}
