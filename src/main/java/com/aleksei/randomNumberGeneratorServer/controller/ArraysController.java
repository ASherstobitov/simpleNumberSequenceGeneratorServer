package com.aleksei.randomNumberGeneratorServer.controller;

import com.aleksei.randomNumberGeneratorServer.model.ArraysModel;
import com.aleksei.randomNumberGeneratorServer.service.ArraysService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/arrays")
public class ArraysController {

    @NonNull
    private ArraysService arraysService;

    @PostMapping
    public ArraysModel generate(@RequestBody Integer size) {
        return arraysService.generate(size);
    }

    @GetMapping
    public ArraysModel getSequences() {
        return arraysService.getRandomSequences();
    }
}
