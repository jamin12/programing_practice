package hello.servlet.basic.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long id;
    private String userName;
    private int age;

    public Member( String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
