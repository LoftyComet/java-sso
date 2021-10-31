package com.cqu.login.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data // 添加getter/setter
@NoArgsConstructor // 添加无参构造器
@AllArgsConstructor // 添加全参构造器
@Accessors(chain = true)
public class User {
    private Integer id;
    private String username;
    private String password;
    
}
