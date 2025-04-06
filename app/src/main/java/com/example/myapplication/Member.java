package com.example.myapplication;

public class Member {
    private int id;
    private String name;
    private String subtitle;
    private String shortDescription;
    private String fullDescription;
    private String imageName;
    private String webUrl;

    public Member(int id, String name, String subtitle, String shortDescription, String fullDescription, String imageName, String webUrl) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.imageName = imageName;
        this.webUrl = webUrl;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSubtitle() { return subtitle; }
    public String getShortDescription() { return shortDescription; }
    public String getFullDescription() { return fullDescription; }
    public String getImageName() { return imageName; }
    public String getWebUrl() { return webUrl; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public void setFullDescription(String fullDescription) { this.fullDescription = fullDescription; }
    public void setImageName(String imageName) { this.imageName = imageName; }
    public void setWebUrl(String webUrl) { this.webUrl = webUrl; }
}
