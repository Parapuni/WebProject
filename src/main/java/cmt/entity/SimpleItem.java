package cmt.entity;

public class SimpleItem {
    private long iid;
    private String title;
    private double rating;
    private String type;
    private String cover;
    public SimpleItem(){}

    public SimpleItem(long iid, String title, double rating,String cover) {
        this.iid = iid;
        this.title = title;
        this.rating = rating;
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
