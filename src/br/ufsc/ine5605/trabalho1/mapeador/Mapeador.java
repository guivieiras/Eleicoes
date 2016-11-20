/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho1.mapeador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author The Gui
 * @param <K>
 * @param <V>
 */
public class Mapeador<K, V> {

    private HashMap<K, V> cache = new HashMap<>();
    private final String filename;

    public Mapeador(String filename) {
        this.filename = filename;
    }

    public void persist() {
        try {
            FileOutputStream fs = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fs);

            oos.writeObject(cache);

            oos.flush();
            oos.close();
            fs.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {
        try {
            FileInputStream fs = new FileInputStream(filename);
            ObjectInputStream oos = new ObjectInputStream(fs);

            cache = (HashMap<K, V>) oos.readObject();

            oos.close();
            fs.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Não foi possivel encontrar o arquivo " + filename);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean put(K chave, V valor) {
        return cache.put(chave, valor) == null;
    }

    public V get(K chave) {
        return cache.get(chave);
    }

    public boolean remove(K chave) {
        return cache.remove(chave) != null;
    }

    public ArrayList<V> getList() {
        return new ArrayList<>(cache.values());
    }

    public boolean contains(K chave) {
        return cache.containsKey(chave);
    }

}
