package com.sk.skala.shopapi.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagedList<T> {
    private List<T> items;
    private long totalCount;
    private int offset;
    private int count;
}
