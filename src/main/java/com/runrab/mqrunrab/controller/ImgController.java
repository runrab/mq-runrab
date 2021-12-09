package com.runrab.mqrunrab.controller;
import com.runrab.mqrunrab.utils.RedisTools;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @author o
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("")
public class ImgController {
    @RequestMapping("/img")
    @ResponseBody
    public void img(HttpServletResponse response) throws IOException {
        Random r=new Random();
        int num=RedisTools.hSize("canal_img");
        String hasKey="canal_img_"+(r.nextInt(num)+1);
        Map<String,Object> map= (Map<String,Object>)RedisTools.hget("canal_img",hasKey);
        String url=map.get("url").toString();
        response.sendRedirect(url);
    }
}
