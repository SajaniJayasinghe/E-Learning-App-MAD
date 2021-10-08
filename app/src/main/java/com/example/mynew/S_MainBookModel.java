package com.example.mynew;

public class S_MainBookModel {
    String author,bookName,imgurl,stream,description,pid;

    //constructors(zero argument)
    public S_MainBookModel() {
    }
    //constructors
    public S_MainBookModel(String author, String bookName, String description, String imgurl, String stream,String pid) {
        this.author = author;
        this.bookName = bookName;
        this.imgurl = imgurl;
        this.stream = stream;
        this.description = description;
        this.pid = pid;
    }

    //getters and setters
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
