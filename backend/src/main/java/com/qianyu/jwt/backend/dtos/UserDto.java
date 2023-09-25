package com.qianyu.jwt.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String nom;
    private String prenom;
    private String userName;
    private String token;

}
//
//import com.auth0.jwt.interfaces.Verification;
//
//import lombok.Builder;
//
//public class UserDto {
//    private long id;
//    private String nom;
//    private String prenom;
//    private String login;
//    private String token;
//
//    public UserDto() {
//        // Default constructor
//    }
//
//    public UserDto(long id, String nom, String prenom, String login, String token) {
//        this.id = id;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.login = login;
//        this.token = token;
//    }
//    
//    
//    public UserDto(String nom, String prenom, String login, String token) {
//		super();
//		this.nom = nom;
//		this.prenom = prenom;
//		this.login = login;
//		this.token = token;
//	}
//    
//
//	public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public static class builder {
//        private long id;
//        private String nom;
//        private String prenom;
//        private String login;
//        private String token;
//
//        public static builder newInstance() {
//        	
//            return new builder();
//        }
//        
//        private builder() {};
//        
//        public builder withId(long id) {
//            this.id = id;
//            return this;
//        }
//
//        public builder withNom(String nom) {
//            this.nom = nom;
//            return this;
//        }
//
//        public builder withPrenom(String prenom) {
//            this.prenom = prenom;
//            return this;
//        }
//
//        public builder withLogin(String login) {
//            this.login = login;
//            return this;
//        }
//
//        public builder withToken(String token) {
//            this.token = token;
//            return this;
//        }
//
//        public UserDto build() {
//            // Perform validation if necessary before building
//            return new UserDto(nom, prenom, login, token);
//        }
//    }
//
//	
//}
