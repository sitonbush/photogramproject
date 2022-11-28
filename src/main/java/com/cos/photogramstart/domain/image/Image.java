package com.cos.photogramstart.domain.image;


import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor //모든 생성자를 자동으로 만들어주는 어노테이션
@NoArgsConstructor //빈 생성자를 자동으로 만들어주는 어노테이션
@Data   //Getter,Setter,toString을 만들어주는 어노테이션
@Entity
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터가 들어갈 때마다 번호를 자동으로 매겨준다.
    @Id // Primary Key를 지정해주는 어노테이션
    private Integer id;

    private String caption;
    private String postImageUrl; //사진을 전송받아서 사진을 서버의 특정 폴더에 저장- DB에 그 저장된 경로를 insert

    @JoinColumn(name="userId") //user로 저장되면 DB에 포린키로 저장됨.
    @ManyToOne
    private User user;

    //이미지 좋아요 -> 추후 업데이트
    //이미지 댓글->추후 업데이트

    private LocalDateTime createDate; //->db에 저장할때는 항상 시간도 같이 저장하자.

    @PrePersist  //DB에 데이터가 Insert되기 직전에 실행해준다.
    private void createDate(){
        //우리가 직접 일일히 DB에 데이터를 Insert하는것이 아니기 때문에 데이터가 들어가는 시간도 들어가야 한다.
        this.createDate=LocalDateTime.now();
    }
}
