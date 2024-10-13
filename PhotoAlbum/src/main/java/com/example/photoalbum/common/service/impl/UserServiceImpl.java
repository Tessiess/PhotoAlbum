package com.example.photoalbum.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.photoalbum.common.dto.UserDto;
import com.example.photoalbum.common.po.User;
import com.example.photoalbum.common.res.Result;
import com.example.photoalbum.common.service.UserService;
import com.example.photoalbum.common.vo.UserVo;
import com.example.photoalbum.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
* @author 杨大宇
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-13 20:38:09
*/
@Service
@Component
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result<UserVo> login(UserDto userDto) {

        String name = userDto.getName();
        String password = userDto.getPassword();

        User user = userMapper.getByName(name);

        if(user == null) return Result.error("用户不存在");

        if(!password.equals(user.getPassword())) return Result.error("账号或密码错误");

        UserVo res = new UserVo();
        BeanUtils.copyProperties(user,res);

        return Result.success(res);
    }

    @Override
    public Result<UserVo> register(UserDto userDto, String conform) {

        String name = userDto.getName();
        String password = userDto.getPassword();

        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setRole("user");

        if(!password.equals(conform)) return Result.error("两次密码不一致");

        userMapper.addUser(user);

        UserVo res = new UserVo();
        res.setName(name);
        res.setRole("user");

        return Result.success(res);
    }
}




