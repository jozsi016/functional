package com.nagy.functional;

import java.util.Comparator;
import java.util.function.ToIntFunction;

@FunctionalInterface
public interface Predicate<E> {

    boolean test(E e);

    default Predicate<E> negate() {
        return x -> !this.test(x);
    }

    default Predicate<E> and(Predicate<E> second) {
        return x -> this.test(x) && second.test(x);
    }

    default Predicate<E> or(Predicate<E> second) {
        return x -> this.test(x) || second.test(x);
    }

    default Predicate<E> compareGreater(ToIntFunction<E> comp) {
        return x -> comp.applyAsInt(x) > 0;
    }

//    default Predicate<E> compareGreater(E e, Comparator<E> comp) {
//        return x -> comp.compare(e, x) > 0;
//    }

//    static <E> Criterion<E> negate(Criterion<E> crit) {
//        return x -> !crit.test(x);
//    }
//
//    static <E> Criterion<E> and(Criterion<E> first, Criterion<E> second) {
//        return x -> first.test(x) && second.test(x);
//    }
//
//    static <E> Criterion<E> or(Criterion<E> first, Criterion<E> second) {
//        return x -> first.test(x) || second.test(x);
//    }
}
