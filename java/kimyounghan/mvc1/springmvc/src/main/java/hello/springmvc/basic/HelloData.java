package hello.springmvc.basic;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HelloData {
    private String username;
    @NotNull
    private Integer age;
}
