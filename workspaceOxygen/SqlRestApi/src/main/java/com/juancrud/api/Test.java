package com.juancrud.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Test")
public class Test {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "Id")
    private Integer id;
	
	@Column (name = "Name")
    private String name;
	
	@Column (name = "Age")
    private int age;
    
    public Test() {
    }
 
    public Test(String name, int age) {
        this.name = name;
        this.age = age;
    }
 
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", Age=" + age +
                '}';
    }
    
}
