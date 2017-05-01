package cz.tul.data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by The CAT
 * */
@Entity
@Table(name = "tags")
public class MyTag {


    @Id
    @org.springframework.data.annotation.Id
    @Column(columnDefinition = "BINARY(16)")
    private int id;

    @Column(name = "value", unique=true)
    private String value;

    @ManyToMany(mappedBy = "tagSet")
    private Set<Image> imageSet = new HashSet<>();

    public MyTag() {
    }

    public MyTag(int id) {
        this.id = id;
    }

    public MyTag(String value) {
        this.value = value;
    }

    public MyTag(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public MyTag(int id, String value, Set<Image> imageSet) {
        this.id = id;
        this.value = value;
        this.imageSet = imageSet;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyTag tag = (MyTag) o;

        if (id != 0 ? !(id==tag.id) : tag.id != 0) return false;
        if (value != null ? !value.equals(tag.value) : tag.value != null) return false;
        return true;

    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
