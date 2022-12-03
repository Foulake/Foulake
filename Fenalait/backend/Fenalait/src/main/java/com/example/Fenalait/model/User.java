package com.example.Fenalait.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="prenom", length= 45 , nullable = false)
	@NotBlank(message = "Veuillez entre votre prénom")
	private String prenom;
	
	@Column(name="nom")
	@NotBlank(message = "Veuillez entre votre nom")
	private String nom;
	
	@Column(name="email",nullable = false, unique = true)
	@NotBlank(message = "Veuillez entre votre E-mail")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-z0-9.-]+$", message = "Entre un E-mail valide")
	private String email;
	
	
	@Column(name="password")
	@NotBlank(message = "Veuillez entre votre mot de passe")
	@Pattern(regexp = "\\S+", message = "Les espaces ne sont pas autorisés")
	@NotBlank(message = "Veuillez entrer votre mot de passe")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Approvissionnement> approvissionnements ;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produit> produits ;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vente> ventes ;
    @ManyToMany
    @JoinTable(

        name = "users_roles",

        joinColumns = @JoinColumn(name = "user_id"),

        inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles = new HashSet<>();

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {

            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    public Set<Role> getRoles() {

        return roles;

    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;

    }
    
    public void addRole(Role role) {
        this.roles.add(role);
    }

     

    public User(String prenom, String nom, String email, String password) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.password = password;
	}


	// previous code is shown 
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
 
	@Override
	public String getUsername() {
		
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//new codes
	
	
	public List<Produit> getProduits() {
		return produits;
	}
	
	/**
	public User(String prenom, String nom, String email, String confirmEmail, String password, String confirmPassword, List<Produit> produits) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.confirmEmail = confirmEmail;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.produits = produits;
	}  **/
	
	public User(String prenom, String nom, String email, String password, List<Produit> produits) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.produits = produits;
	}

	public void addProduit(Produit produit) {
		produits.add(produit);
	}
	public void removeProduit(Produit produit) {
		produits.remove(produit);
	}

	public void addApprovissionnement(Approvissionnement approvissionnement) {
		approvissionnements.add(approvissionnement);
	}
	
	public void removeApprovissionnement(Approvissionnement approvissionnement) {
		approvissionnements.remove(approvissionnement);
	}
	
}
