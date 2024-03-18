package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity(name = "user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder  
public class UserEntity implements UserDetails {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(name = "user_Id", nullable = false, length = 30, unique = true)
	 private String userId;
	 
	 @Column(name = "password", nullable = false, length = 50)
	 private String password;
	 
	 @Column(name = "name", nullable = false)
	 private String name;
	 
	 @Column(name = "birth", nullable = false)
	 private String birth;   
	 
	 @Column(name = "email", nullable = false)
	 private String email;
	 
	 @Column(name = "addr1", nullable = true)
	 private String addr1;
	 
	 @Column(name = "addr2", nullable = true)
	 private String addr2;
	 
	 // PuppyEntity 일대다 관계 추가
//	 @OneToMany(cascade = CascadeType.ALL)
//	 private List<PuppyEntity> puppyEntityList;
//
//	 // CommentEntity 일대다 관계 추가 & 조인
//	 @OneToMany(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "id")   // 외래키 지정
//	 private List<CommentEntity> commentEntityList;
//
//	 // BoardEntity 일대다 관계 추가 & 조인
//	 @OneToMany(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "id")
//	 private List<BoardEntity> boardEntityList;
//
//	 // LikeListEntity 일대다 관계 추가 & 조인
//	 @OneToMany(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "id")
//	 private List<LikeListEntity> likeListEntityList;
	 

	 
	 
	/**
	* 해당 유저의 권한 목록
	*/	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* 사용자의 비밀번호
	*/
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* PK값
	*/
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * 계정 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     * @return
     */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * 비밀번호(인증정보) 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * 사용자 활성화 여부
     * true : 활성화
     * false : 비활성화
     * @return
     */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	 
}
