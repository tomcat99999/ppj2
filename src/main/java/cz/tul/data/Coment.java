package cz.tul.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by The CAT
 */
@Entity
@Table(name = "coments")
public class Coment {
    @Id
    @org.springframework.data.annotation.Id
    @Column(columnDefinition = "BINARY(16)")
    private int id;

    @Column(name = "coment")
    private String coment;

    @Column(name = "created")
    private Date createdDate;

    @Column(name = "changed")
    private Date changedDate;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")

    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "name_id")

    private Image image;

    public Coment() {
    }

    public Coment(int id) {
        this.id = id;
    }

    public Coment(int id, String coment, User user, Image image) {
        this.id = id;
        this.coment = coment;
        this.user = user;
        this.createdDate = new Date();
        this.changedDate = new Date();
        this.likes = 0;
        this.dislikes = 0;
        this.image = image;
        image.getCommentSet().add(this);
    }

   /* public Coment(int id, String coment, User user) {
        this.id = id;
        this.coment = coment;
        this.user = user;
    }*/


    public void setComent(String comment) {
        this.coment = comment;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComent() {
        return coment;
    }



    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public Integer getLikes() {
        return likes;
    }
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public void Like() {
        this.likes = this.likes+1;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void Dislike() {
        this.dislikes =  this.dislikes+1;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coment comment1 = (Coment) o;

        if (id != 0 ? !(id==comment1.id) : comment1.id != 0) return false;
        if (coment != null ? !coment.equals(comment1.coment) : comment1.coment != null) return false;
        if (createdDate != null ? !createdDate.equals(comment1.createdDate) : comment1.createdDate != null)
            return false;
        if (changedDate != null ? !changedDate.equals(comment1.changedDate) : comment1.changedDate != null)
            return false;
        if (likes != null ? !likes.equals(comment1.likes) : comment1.likes != null) return false;
        if (dislikes != null ? !dislikes.equals(comment1.dislikes) : comment1.dislikes != null)
            return false;
        if (user != null ? !user.equals(comment1.user) : comment1.user != null) return false;
        if (image != null ? !image.equals(comment1.image) : comment1.image != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Coment{" +
                "id=" + id +
                ", comment='" + coment + '\'' +
                ", createdDate=" + createdDate +
                ", changedDate=" + changedDate +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", user=" + user +
                ", image=" + image.getName() +
                '}';
    }
}
