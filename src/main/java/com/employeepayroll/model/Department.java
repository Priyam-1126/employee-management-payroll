package com.employeepayroll.model;
public class Department {
    private int id; private String name;
    public Department(){} public Department(int id,String name){this.id=id;this.name=name;}
    public int getId(){return id;} public String getName(){return name;}
    public void setId(int v){id=v;} public void setName(String v){name=v;}
}
