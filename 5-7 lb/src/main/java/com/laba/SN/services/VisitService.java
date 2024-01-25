package com.laba.SN.services;

import com.laba.SN.models.Visit;
import com.laba.SN.repos.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public int incrementCounter() {
        Visit counterEntity = getCount();
        counterEntity.setCount(counterEntity.getCount() + 1);
        visitRepository.save(counterEntity);
        return counterEntity.getCount();
    }

    public int getCounter() {
        return getCount().getCount();
    }

    private Visit getCount() {
        return visitRepository.findById(1L).orElseGet(() -> {
            Visit newCounterEntity = new Visit();
            newCounterEntity.setCount(0);
            return visitRepository.save(newCounterEntity);
        });
    }
}

