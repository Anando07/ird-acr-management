package com.avijit.ird.pageable;

import com.avijit.ird.domain.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * @author Avijit Barua
 * @created_on 12/25/18 at 11:32 AM
 * @project ird
 */
public class UserResponsePage implements Page {

    private List<UserDTO> content;
    private Pageable pageable;
    private boolean last;
    private Integer totalPages;
    private Long totalElements;
    private Integer size;
    private Integer number;
    private Integer numberOfElements;
    private boolean first;
    private Sort sort;

    public UserResponsePage(List<UserDTO> content, Page page) {
        this.content = content;
        this.pageable = page.getPageable();
        this.last = page.isLast();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.size = page.getSize();
        this.number = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.first = page.isFirst();
        this.sort = page.getSort();
    }

    public void setContent(List<UserDTO> content) {
        this.content = content;
    }


    @Override
    public Pageable getPageable() {
        return pageable;
    }

    @Override
    public int getTotalPages() {
        return this.totalPages;
    }

    @Override
    public long getTotalElements() {
        return this.totalElements;
    }

    @Override
    public Page map(Function function) {
        return null;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    @Override
    public List getContent() {
        return this.content;
    }

    @Override
    public boolean hasContent() {
        return this.content.size() > 0;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public boolean isFirst() {
        return this.first;
    }

    @Override
    public boolean isLast() {
        return this.last;
    }

    @Override
    public boolean hasNext() {
        return !this.isLast();
    }

    @Override
    public boolean hasPrevious() {
        return !this.isFirst();
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
