package com.hao.mytest.effectivejava.twentysix;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by haojiahong on 16/5/4.
 */
public class StackGeneric<E> {
    private E[] elements;
    private int size;
    private final static int DEFAULT_INITIAL_CAPACITY = 16;

    public StackGeneric() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

}
