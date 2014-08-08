/*
* Description : This software is an implementation of the algorithms 
*               in the Introduction to Algorithms book by
*               Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
* 
*               Information about the book can be found here :-
*               http://en.wikipedia.org/wiki/Introduction_to_Algorithms
*               http://mitpress.mit.edu/books/introduction-algorithms
*
*
* Author :      Rushang Vinod Vandana Karia
*                   - Rushang.Karia@asu.edu
*                   - 4806283130
*                   - github.com/RushangKaria
*                   - Arizona State University
*
* Author :      Shrijal Pravin Gandhi
*                   - Shrijal.Gandhi@asu.edu
*                   - 4806282324
*                   - github.com/ShrijalGandhi
*                   - Arizona State University
*
* File : SimpleHashList.java
*           A simple but inefficient implementation of HashList.
*
*    Copyright (C) 2014  Rushang Karia, Shrijal Gandhi
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.

*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package com.util.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;


/**
* A simple wrapper for @see java.util.HashMap
* to enable multiple values per key.
*/
public class SimpleHashList<K,V>
{
    private HashMap<K,LinkedList<V>> map;

    /**
    * Constructor to initialize the HashList
    */
    public SimpleHashList()
    {
        this.map = new HashMap<K,LinkedList<V>>();
    }

    /**
    * This method inserts a value into the HashList at the specified key.
    * If the specified key is already hashed to an element, then the collision is resolved by chaining.
    * @param key the key where the value is to be put
    * @param value the value that is to be inserted
    */
    public void put(K key, V value)
    {
    LinkedList<V> list;
    
        if(map.containsKey(key))
        list = this.map.get(key);    
        else
        list = new LinkedList<V>();

    list.add(value);
    this.map.put(key,list);
    }

    /**
    * @return returns a list of the values at that particular key.
    */
    public LinkedList<V> get(K key)
    {
        return this.map.containsKey(key) ? this.map.get(key) : null;
    }

    /**
    * @return returns a set of keys that are contained in the Hashlist.
    */
    public Set<K> keySet()
    {
        return this.map.keySet();
    }

    /**
    * @return returns true if the Hashlist contains mappings for specified key.
    */
    public boolean containsKey(K key)
    {
        return this.map.containsKey(key);
    }

    /**
    * @return returns true if the Hashlist is empty - contains no mappings.
    */
    public boolean isEmpty()
    {
        return this.map.isEmpty();
    }

    /**
    * removes the key and all its mappings from the Hashlist.
    * <p>
    * If the key does not exist then this method does nothing.
    */
    public void remove(K key)
    {
        this.map.remove(key);
    }

    /**
    * @return returns the number of different keys in this Hashlist
    */
    public int size()
    {
        return this.map.size();
    }

    /**
    * deletes all the mappings from the Hashlist.
    */
    public void clear()
    {
        this.map.clear();
    }
}
