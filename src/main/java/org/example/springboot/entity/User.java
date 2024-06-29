package org.example.springboot.entity;

import javax.persistence.*;

@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private Integer age;
    private Integer cet4;
    private Integer cet6;
    private Integer wordsize;



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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCet6() {
        return cet6;
    }

    public void setCet6(Integer cet6) {
        this.cet6 = cet6;
    }

    public Integer getCet4() {
        return cet4;
    }

    public void setCet4(Integer cet4) {
        this.cet4 = cet4;
    }

    public Integer getWordsize() {
        return wordsize;
    }

    public void setWordsize(Integer wordsize) {
        this.wordsize = wordsize;
    }
}
