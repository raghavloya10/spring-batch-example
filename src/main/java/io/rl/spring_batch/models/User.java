package io.rl.spring_batch.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    
    @Id
    Integer id;
    String name;
    String dept;
    Integer salary;

    public User() {
        
    }

    public User(Integer id, String name, String dept, Integer salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDept() {
        return this.dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public Integer getSalary() {
        return this.salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

	@Override
	public String toString() {
		return "User [id=" + this.id + ", name=" + this.name + ", dept=" + this.dept + ", salary=" + this.salary + "]";
	}

    
}
