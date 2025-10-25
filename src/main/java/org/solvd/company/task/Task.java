package org.solvd.company.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.solvd.company.adapter.LocalDateAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    private String name;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "startTime")
    private LocalDate startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "endTime")
    private LocalDate endTime;

    public Task(String name, LocalDate startTime, LocalDate endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Task() {

    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}
