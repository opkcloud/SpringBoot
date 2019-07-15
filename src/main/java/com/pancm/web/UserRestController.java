package com.pancm.web;

import java.util.List;

import com.pancm.util.JsonResult;
import com.pancm.util.logs.SystemControllerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pancm.pojo.User;
import com.pancm.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Title: UserRestController
 * Description:
 * 用户控制层
 * Version:1.0.0
 *
 * @author pancm
 * @date 2018年3月19日
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @SystemControllerLog(description = "1", operateName = "用户信息", operateNape = "新增用户")
    @ApiOperation(value = "新增用户信息", httpMethod = "POST", notes = "新增用户信息")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public JsonResult addUser(@RequestBody User user) {
        JsonResult result = new JsonResult();
        boolean flag = userService.addUser(user);
        if (flag) {
            System.out.println("开始新增...");
            result.setSuccess(true);
            result.setMessage("新增成功");
        }
        return result;
    }

    @ApiOperation(value = "更新用户信息", httpMethod = "PUT", notes = "更新用户信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
        System.out.println("开始更新...");
        return userService.updateUser(user);
    }

    @ApiOperation(value = "根据用户Id删除用户信息", httpMethod = "DELETE", notes = "根据用户Id删除用户信息")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public boolean deleteUser(@RequestParam(value = "userId", required = true) int userId) {
        System.out.println("开始删除...");
        return userService.deleteUser(userId);
    }

    @ApiOperation(value = "根据名字查询用户信息", httpMethod = "GET", notes = "根据名字查询用户信息")
    @RequestMapping(value = "/findByUserName", method = RequestMethod.GET)
    public User findByUserName(@RequestParam(value = "userName", required = true) String userName) {
        System.out.println("开始查询...");
        return userService.findUserByName(userName);
    }

    @ApiOperation(value = "查询所有用户信息", httpMethod = "GET", notes = "查询所有用户信息")
    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public List<User> findByUserAge() {
        return userService.findAll();
    }
}
