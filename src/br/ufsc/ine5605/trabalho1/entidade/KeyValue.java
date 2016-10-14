package br.ufsc.ine5605.trabalho1.entidade;

public class KeyValue<T, E> {

    public T key;
    public E value;

    public KeyValue(T key, E value) {
        this.key = key;
        this.value = value;
    }
}
