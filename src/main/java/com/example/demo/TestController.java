package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/21 0:19
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public JsonResult test() {
        return new JsonResult<>("456456456456");
    }
}
