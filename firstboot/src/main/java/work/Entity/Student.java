package work.Entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("student")


public class Student {
  public Student() {
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", course='" + course + '\'' +
            ", duration=" + duration +
            '}';
  }

  @Value("${student.id}")

  private int id;
  @Value("${student.name}")
    private String name;
  @Value("${student.course}")
    private String course;
  @Value("${student.duration}")
    private int duration;

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

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
}
