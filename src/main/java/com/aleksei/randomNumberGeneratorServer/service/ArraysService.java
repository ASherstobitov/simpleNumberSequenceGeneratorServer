package com.aleksei.randomNumberGeneratorServer.service;


import com.aleksei.randomNumberGeneratorServer.model.ArraysModel;


public interface ArraysService {

    ArraysModel generate(Integer size);
    ArraysModel getRandomSequences();
}
