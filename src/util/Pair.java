/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Edgar
 * @param <First>
 * @param <Second>
 */
public class Pair<First, Second> {
    private First first;
    private Second second;

    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
    }

    public First getFirst() {
        return first;
    }

    public Second getSecond() {
        return second;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Pair)) return false;
        Pair pair = (Pair) other;
        return 
                this.first == pair.getFirst() &&
                this.second == pair.getSecond()
                ;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public void setSecond(Second second) {
        this.second = second;
    }
    
    
}
