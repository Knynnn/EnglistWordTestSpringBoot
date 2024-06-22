package org.example.springboot.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.Params;
import org.example.springboot.service.KaoyanWordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/kaoyanWord")
public class KaoyanWordController {
    @Resource
    private KaoyanWordService kaoyanWordService;

    @PostMapping
    public Result save(@RequestBody KaoyanWord kaoyanWord) {
        if(kaoyanWord.getId() == null) {
            kaoyanWordService.add(kaoyanWord);
        } else {
            kaoyanWordService.update(kaoyanWord);
        }
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        List<KaoyanWord> list = kaoyanWordService.finnAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<KaoyanWord> info = kaoyanWordService.findBySearch(params);
        return Result.success(info);

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        kaoyanWordService.delete(id);
        return Result.success();
    }
}
