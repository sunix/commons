/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.commons.lang.cache;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** A compound iterator, which iterates over two or more other iterators and represents few iterators as one. */
public class CompoundIterator<T> implements Iterator<T> {

    private final Iterator[] iterators;
    private       int        index;

    public CompoundIterator(Iterator<T> iterator1, Iterator<T> iterator2) {
        iterators = new Iterator[]{iterator1, iterator2};
    }

    public CompoundIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators.toArray(new Iterator[iterators.size()]);
    }

    public boolean hasNext() {
        while (index < iterators.length) {
            if (iterators[index].hasNext()) {
                return true;
            }
            index++;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T)iterators[index].next();
    }

    public void remove() {
        iterators[index].remove();
    }
}
