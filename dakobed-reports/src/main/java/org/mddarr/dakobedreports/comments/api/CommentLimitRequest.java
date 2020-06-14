package org.mddarr.dakobedreports.comments.api;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class CommentLimitRequest implements Pageable {
    private int limit;

    private int offset;

    public CommentLimitRequest(int offset, int limit){

        this.limit = limit;

        this.offset = offset;

    }

    @Override
    public boolean isPaged() {
        return false;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    @Override

    public int getPageNumber() {

        return 0;

    }

    @Override

    public int getPageSize() {

        return limit;

    }

    @Override
    public long getOffset() {
        return 0;
    }


    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Optional.empty();
    }
}
