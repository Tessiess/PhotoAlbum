package com.example.photoalbum.common.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class UserDto implements Serializable {
    private String name;

    private String password;
}