package com.kushjaiswal.cricketgame.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ProbabilityManipulation {
    public int myRand(List<Integer> runs, List<Integer> freq, int n) {
        List<Integer> prefix = new ArrayList(Collections.nCopies(n, 0));
        prefix.set(0, freq.get(0));

        for(int i = 1; i < n; ++i) {
            prefix.set(i, (Integer)prefix.get(i - 1) + (Integer)freq.get(i));
        }

        int r = (int)(Math.random() * 323567.0) % (Integer)prefix.get(n - 1) + 1;
        int indexc = this.findCeil(prefix, r, 0, n - 1);
        return (Integer)runs.get(indexc);
    }

    public int findCeil(List<Integer> prefix, int r, int l, int h) {
        while(l < h) {
            int mid = l + (h - l >> 1);
            if (r > (Integer)prefix.get(mid)) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return (Integer)prefix.get(l) >= r ? l : -1;
    }
}
