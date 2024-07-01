package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_Id", nullable = true, length = 30, unique = true)
	private String userId;

	@Column(name = "password", nullable = true, length = 50)
	private String password;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "birth", nullable = true)
	private String birth;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "addr1", nullable = true)
	private String addr1;

	@Column(name = "addr2", nullable = true)
	private String addr2;

	@Column(name = "kakao_Id", nullable = true)
	private String kakaoId;

	@Column(name = "naver_Id", nullable = true)
	private String naverId;

	// PuppyEntity 일대다 관계 추가
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "puppy_id")
	private List<PuppyEntity> puppyEntityList;

	// CommentEntity 일대다 관계 추가 & 조인
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")   // 외래키 지정
	private List<CommentEntity> commentEntityList;

	// BoardEntity 일대다 관계 추가 & 조인
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "board_id")
	private List<BoardEntity> boardEntityList;

	// LikeListEntity 일대다 관계 추가 & 조인
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "likeList_id")
	private List<LikeListEntity> likeListEntityList;

	// UserRole 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<UserRole> roleList;

	/**
	 * 해당 유저의 권한 목록
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		for(int i = 0; i < roleList.size(); i++) {
			authorities.add(new SimpleGrantedAuthority(roleList.get(i).getRoles().getRoleName()));
		}
		return authorities;

		// roles 리스트를 스트림으로 변환하고
		// 각 역할을 SimpleGrantedAuthority로 변환한 후
		// 그 결과를 리스트로 수집하여 반환
//		return roles.stream()
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
	}

	/**
	 * 사용자의 비밀번호
	 */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	/**
	 * PK값
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userId;
	}

	/**
	 * 계정 만료 여부
	 * true : 만료 안됨
	 * false : 만료
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 계정 잠김 여부
	 * true : 잠기지 않음
	 * false : 잠김
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 비밀번호(인증정보) 만료 여부
	 * true : 만료 안됨
	 * false : 만료
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 사용자 활성화 여부
	 * true : 활성화
	 * false : 비활성화
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
