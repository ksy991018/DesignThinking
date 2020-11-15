package com.icemelon.scheduler.dto;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ResultList {

    private List<Result> list;

    public ResultList(List<Result> results) {

        list = results;
    }

    public void sort() {

        list.removeIf(result -> result.availableSize() == 0);

        Collections.sort(list);
    }


}
