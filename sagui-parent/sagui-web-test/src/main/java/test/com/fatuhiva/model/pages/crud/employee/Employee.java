package test.com.fatuhiva.model.pages.crud.employee;

import java.io.Serializable;

public class Employee implements Serializable {

    private long id;
    private String nome;
    private double salary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
