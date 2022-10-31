package com.example.Fenalait.model;

import javax.persistence.*;

 

@Entity
@Table(name = "roles")
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

     

    @Column(nullable = false, length = 50, unique = true)

    private String name;

 

    public Role() { }

     

    public Role(String name) {
        this.name = name;
    }
    
    public Role(Long id) {
        this.id = id;
    }

	@Override
    public String toString() {
        return this.name;

    }
    // getters and setters are not 
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
}