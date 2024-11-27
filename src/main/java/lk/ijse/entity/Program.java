package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Program {

    @Id
    private String program_id;
    private String program_name;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    List<Registration> registrations;


    public Program() {
    }

    public Program(String program_id, String program_name, String duration, double fee) {
        this.program_id = program_id;
        this.program_name = program_name;
        this.duration = duration;
        this.fee = fee;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Program{" +
                "program_id='" + program_id + '\'' +
                ", program_name='" + program_name + '\'' +
                ", duration=" + duration +
                ", fee=" + fee +
                '}';
    }
}