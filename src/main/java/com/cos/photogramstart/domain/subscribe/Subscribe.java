package com.cos.photogramstart.domain.subscribe;

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
@Entity //DB에 테이블을 생성해주는 어노테이션

)
public class Subscribe {
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터가 들어갈 때마다 번호를 자동으로 매겨준다.
    @Id // Primary Key를 지정해주는 어노테이션
    private Integer id;

    @JoinColumn(name="fromUserId")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name="toUserId")
    @ManyToOne
    private User toUser;


    private LocalDateTime createDate;

    @PrePersist  //DB에 데이터가 Insert되기 직전에 실행해준다.
    private void createDate(){
        //우리가 직접 일일히 DB에 데이터를 Insert하는것이 아니기 때문에 데이터가 들어가는 시간도 들어가야 한다.
        this.createDate=LocalDateTime.now();
    }
}
