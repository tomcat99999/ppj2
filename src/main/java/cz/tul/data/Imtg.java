package cz.tul.data;

/**
 * Created by The CAT
 */
public class Imtg {


private Image img;
private MyTag tag;

    public Imtg(Image img, MyTag tag) {
        this.img = img;
        this.tag = tag;
    }

    public Imtg() {
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public MyTag getTag() {
        return tag;
    }

    public void setTag(MyTag tag) {
        this.tag = tag;
    }
}

