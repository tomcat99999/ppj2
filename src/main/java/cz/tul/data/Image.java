package cz.tul.data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by The CAT
 * */
@Entity
@Table(name = "images")
public class Image {

    @Id
    @org.springframework.data.annotation.Id
    @Column(name="id")
    private int id;

    @Column(name = "name", unique=true)
    private String name;

    @Column(name = "location")
    private String Location;

    @Column(name = "created")
    private Date createdDate;

    @Column(name = "changed")
    private Date changedDate;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Coment> commentSet = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "image_tags",
            joinColumns = {@JoinColumn(name = "image_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"image_id", "tag_id"})})

    private Set<MyTag> tagSet = new HashSet<>();

    public Image() {
    }

    public Image(int id) {
        this.id = id;
    }

    public Image(String name) {
        this.name = name;
    }

    public Image(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Image(int id, String name, String Location, User user, Set<Coment> commentSet, Set<MyTag> tagSet ) {
        this.id = id;
        this.name = name;
        this.Location = Location;
        this.user = user;
        this.commentSet = commentSet;
        this.tagSet = tagSet;
        this.likes = 0;
        this.dislikes = 0;
        this.createdDate = new Date();
    }



    public int getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public Integer getLikes() {
        return likes;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }



    public void Like() {
        this.likes= this.likes+1;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void Dislike() {
        this.dislikes = this.dislikes+1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Coment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Coment> commentSet) {
        this.commentSet = commentSet;
    }

    public Set<MyTag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<MyTag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + Location + '\'' +
                ", createdDate=" + createdDate +
                ", changedDate=" + changedDate +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", user=" + user +
                ", commentSet=" + commentSet +
                ", tagSet=" + tagSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (id != 0 ? !(id==image.id) : image.id != 0) return false;
        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        if (Location != null ? !Location.equals(image.Location) : image.Location != null) return false;
        if (createdDate != null ? !createdDate.equals(image.createdDate) : image.createdDate != null) return false;
        if (changedDate != null ? !changedDate.equals(image.changedDate) : image.changedDate != null) return false;
        if (likes != null ? !likes.equals(image.likes) : image.likes != null) return false;
        if (dislikes != null ? !dislikes.equals(image.dislikes) : image.dislikes != null)
            return false;
        if (user != null ? !(user.getId()==image.user.getId()) : image.user != null) return false;
        return true;
    }
}
