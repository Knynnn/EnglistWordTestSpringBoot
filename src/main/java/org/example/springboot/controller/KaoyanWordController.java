package org.example.springboot.controller;

import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.Params;
import org.example.springboot.service.KaoyanWordService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/kaoyanWord")
public class KaoyanWordController {
    @Resource
    private KaoyanWordService kaoyanWordService;

    @GetMapping
    public Result findAll() {
        List<KaoyanWord> list = kaoyanWordService.finnAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        List<KaoyanWord> list = kaoyanWordService.findBySearch(params);
        return Result.success(list);

    }
}
