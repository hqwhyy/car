package com.example.car.action;

import com.example.car.dto.QueryDTO;
import com.example.car.dto.UserDTO;
import com.example.car.pojo.SysUser;
import com.example.car.service.UserService;
import com.example.car.utils.DataGridResult;
import com.example.car.utils.R;
import com.example.car.utils.ShiroUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class UserAction {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ResponseBody
    @RequestMapping("/findUser")
    public List<SysUser> showUser(){
        return userService.findAll();
    }

    @ResponseBody
    @RequestMapping("/sys/login")
    public R login(@RequestBody UserDTO userDTO){
//        System.out.println(userDTO.getPassword()+userDTO.getUsername());
        //验证码的验证
        //1,客户端传的验证码
        String captcha = userDTO.getCaptcha();
        //2,服务端存储的验证码
        String kaptcha = ShiroUtils.getKaptcha();
        if(!kaptcha.equalsIgnoreCase(captcha)){
            return R.error("验证码错处");
        }
        //3,开始真正的登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(),userDTO.getPassword());
        subject.login(token);
        //4, cookie
        if(userDTO.isRememberMe()){
            token.setRememberMe(true);
        }

        //5,判断权限
        boolean permitted = subject.isPermitted("sys:user:list");
        System.out.println(permitted);
        boolean role = subject.hasRole("管理员");
        System.out.println(role);
        return R.ok();//成功状态
    }

    @ResponseBody
    @RequestMapping("/sys/user/list")
    public DataGridResult showUser(QueryDTO queryDTO){
        return userService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/user/export")
    public void exportUser(HttpServletResponse response) throws IOException{
        Workbook workbook = userService.exportUser();
        response.setContentType("application/octet-stream");
        String fileName = "某公司员工信息.xls";
        fileName = URLEncoder.encode(fileName,"utf-8");
        response.setHeader("content-disposition","attachment;filename="+fileName);
        //使用网络输出流
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/captcha.jpg")
    public void kaptcha(HttpServletResponse response) throws IOException {
        // 缓存设置-设置不缓存（可选操作）
        response.setHeader("Cache-Control","no-store, no-cache");
        // 设置响应内容
        response.setContentType("image/jpg");
        //生成文字的验证码
        String text = defaultKaptcha.createText();
        //生成图片
        BufferedImage image = defaultKaptcha.createImage(text);
        //验证码在服务端需要保持 shiro的 session
        ShiroUtils.setKaptcha(text);
        //把图片通过网络流输出到客户端
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        outputStream.flush();
    }

    @RequestMapping("/logout")// 退出
    public String logout(){
        ShiroUtils.logout();
        return "redirect:login.html";
    }

    @RequestMapping("/sys/user/info")
    @ResponseBody
    public R userinfo(){
        SysUser userEntity = ShiroUtils.getUserEntity();
        return R.ok().put("user",userEntity);
    }

    @RequestMapping("/sys/user/save")
    @ResponseBody
    public R insertUser(@RequestBody UserDTO userDTO){
        return userService.insert(userDTO);
    }

    @RequestMapping("/sys/user/del")
    @ResponseBody
    public R deleUser(@RequestBody List<Long> ids){
        return userService.deleUserList(ids);
    }

    @RequestMapping("/sys/user/info/{userId}")
    @ResponseBody
    public R userInfo(@PathVariable("userId") Long userId){
        return userService.findUserById(userId);
    }

    @RequestMapping("/sys/user/update")
    @ResponseBody
    public R update(@RequestBody SysUser sysUser){
        return userService.updateUser(sysUser);
    }
}
