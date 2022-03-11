package models;

import java.util.ArrayList;

public class CV {
  public Integer id;
  public String title;
  public ArrayList<Skill> skills;
  public ArrayList<Section> sections;
  public Integer designNumber;

  public CV() {
    this.sections.add(new Section("about", ""));
    this.sections.add(new Section("contact-info", ""));
    this.sections.add(new Section("experience", ""));
    this.sections.add(new Section("education", ""));
    this.sections.add(new Section("publications", ""));
  }

  public CV(Integer id, String title, ArrayList<Skill> skills, ArrayList<Section> sections, Integer designNumber) {
    this.id           = id;
    this.title        = title;
    this.skills       = skills;
    this.sections     = sections;
    this.designNumber = designNumber;
  }

  public String getTitle() {
    return this.title;
  }
}

class Section {
  public String title;
  public String content;

  public Section() {
  }

  public Section(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
