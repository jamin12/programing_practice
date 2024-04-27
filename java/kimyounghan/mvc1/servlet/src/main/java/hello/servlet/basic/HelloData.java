package hello.servlet.basic;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HelloData {
    private String userName;
    private int age;
}
