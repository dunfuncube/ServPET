package com.servPet.psOdd.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PsOddService")
public class PsOddService {
    @Autowired
    private PsOddRepository psOddRepository;

}
