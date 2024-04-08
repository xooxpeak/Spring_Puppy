package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "role") // 테이블명 명시
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

//    @ManyToOne
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private UserEntity user;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id")
//    private List<UserRole> userRoleList;

//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private List<UserRole> userRoleList;

}
