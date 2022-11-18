package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID =1L;
    private User user;

    public PrincipalDetails(User user){

        this.user=user;
    }



    //권한을 가지고 오는 메서드, 유저마다 권한이 여러개일 수 있어서 컬렉션 타입으로 가지고옴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Collection<GrantedAuthority> collector= new ArrayList<>();
       collector.add(()->{
           return user.getRole();
       });
        return collector;
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    //return이 true일 때, 정상적으로 로그인 로직이 실행됨.
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
}
