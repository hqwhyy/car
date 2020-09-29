package com.example.car.action;

import com.example.car.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
/**
 * 车款管理
 *
 * @author qwhe
 * @date 2020/09/29
 */
@Controller
public class FileAction {
    @RequestMapping("/ytupload")
    @ResponseBody
    public R ShangChuan(@RequestParam("mypic") MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        File file = new File("F:\\test\\" + filename);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("file",filename);
    }
}
