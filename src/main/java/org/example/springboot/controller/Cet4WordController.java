package org.example.springboot.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.Cet4Word;
import org.example.springboot.entity.Params;
import org.example.springboot.service.Cet4WordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/cet4Word")
public class Cet4WordController {
    @Resource
    private Cet4WordService cet4WordService;

    @PostMapping
    public Result save(@RequestBody Cet4Word cet4Word) {
        if (cet4Word.getId() == null) {
            cet4WordService.add(cet4Word);
        } else {
            cet4WordService.update(cet4Word);
        }
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        List<Cet4Word> list = cet4WordService.findAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Cet4Word> info = cet4WordService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        cet4WordService.delete(id);
        return Result.success();
    }
}
