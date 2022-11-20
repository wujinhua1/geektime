package geektime.spring.springbucks.bean.model;

import lombok.*;

import java.io.*;


@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {
    private Integer id;
    private String name;
    private Double price;
}
