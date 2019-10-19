/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.seed.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Seeds implements List<Seed> {
    
    private List<Seed> seeds = new ArrayList<>();

    @Override
    public int size() {
        return seeds.size();
    }

    @Override
    public boolean isEmpty() {
        return seeds.isEmpty();
    }

    @Override
    public boolean contains(Object object) {
        return seeds.contains(object);
    }

    @Override
    public Iterator<Seed> iterator() {
        return seeds.iterator();
    }

    @Override
    public Object[] toArray() {
        return seeds.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) 
            throws ArrayStoreException, 
            NullPointerException {
        return seeds.toArray(ts);
    }

    @Override
    public boolean add(Seed seed) {
        return seeds.add(seed);
    }

    @Override
    public boolean remove(Object object) {
        return seeds.remove(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return seeds.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends Seed> collection) {
        return seeds.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends Seed> collection) {
        return seeds.addAll(i, collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return seeds.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return seeds.retainAll(collection);
    }

    @Override
    public void clear() {
        seeds.clear();
    }

    @Override
    public Seed get(int i) {
        return seeds.get(i);
    }

    @Override
    public Seed set(int i, Seed seed) {
        return seeds.set(i, seed);
    }

    @Override
    public void add(int i, Seed seed) {
        seeds.add(i, seed);
    }

    @Override
    public Seed remove(int i) {
        return seeds.remove(i);
    }

    @Override
    public int indexOf(Object object) {
        return seeds.indexOf(object);
    }

    @Override
    public int lastIndexOf(Object object) {
        return seeds.lastIndexOf(object);
    }

    @Override
    public ListIterator<Seed> listIterator() {
        return seeds.listIterator();
    }

    @Override
    public ListIterator<Seed> listIterator(int i) {
        return seeds.listIterator(i);
    }

    @Override
    public List<Seed> subList(int i, int j) {
        return seeds.subList(i, j);
    }
}
