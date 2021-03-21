package com.aleksei.randomNumberGeneratorServer.service.impl;

import com.aleksei.randomNumberGeneratorServer.model.ArraysModel;
import com.aleksei.randomNumberGeneratorServer.service.ArraysService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ArraysServiceImpl implements ArraysService {

    private final List<ArraysModel> LIST = new ArrayList<>();

    @NonNull
    private ArraysModel arraysModel;

    @Override
    public ArraysModel generate(Integer size) {
        arraysModel.setSize(size);
        List<Long> numberList = sequenceGenerator(size);
        int G = arraysModel.getSize();
        int NG = (numberList.size() + G - 1) / G;

        List<List<Long>> lists = LongStream.range(0, NG)
                .mapToObj(i -> numberList.subList(G * (int) i, (int) Math.min(G * i + G, numberList.size())))
                .collect(Collectors.toList());

        arraysModel.setLists(lists);
        LIST.add(0, arraysModel);
        return arraysModel;
    }

    public ArraysModel getRandomSequences() {
        ArraysModel model = new ArraysModel(LIST.get(0).getSize(), LIST.get(0).getLists());
        Random random = new Random();
        int intRand = random.nextInt(model.getSize() - 6) + 6;
        List<List<Long>> sequences =
                model.getLists().stream()
                .map(x -> x.subList(intRand - 6, intRand))
                .collect(Collectors.toList());
        model.setSize(6);
        model.setLists(sequences);
        return model;
    }

    private List<Long> sequenceGenerator(long n) {
        long randNum = 1000 + (long) (Math.random() * 10000);
        return LongStream.rangeClosed(2, randNum)
                .filter(x -> isSimple(x)).limit(n * 5).boxed()
                .collect(Collectors.toList());
    }

    private boolean isSimple(long number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
