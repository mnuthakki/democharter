package org.charter.democharter.mapper;

public interface BaseMapper<S, T> {

    T map(S object);
}
