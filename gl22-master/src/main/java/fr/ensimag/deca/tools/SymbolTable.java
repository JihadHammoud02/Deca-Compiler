package fr.ensimag.deca.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Manage unique symbols.
 * 
 * A Symbol contains the same information as a String, but the SymbolTable
 * ensures the uniqueness of a Symbol for a given String value. Therefore,
 * Symbol comparison can be done by comparing references, and the hashCode()
 * method of Symbols can be used to define efficient HashMap (no string
 * comparison or hashing required).
 * 
 * @author gl22
 * @date 01/01/2024
 */
public class SymbolTable {
    private Map<String, Symbol> map;

    public SymbolTable() {
        super();
        this.map = new HashMap<String, Symbol>();
    }
    /**
     * Create or reuse a symbol.
     * 
     * If a symbol already exists with the same name in this table, then return
     * this Symbol. Otherwise, create a new Symbol and add it to the table.
     */
    public Symbol create(String name) {
        if(map.containsKey(name)){
            return map.get(name);
        }
        Symbol newSymb = new Symbol(name);
        map.put(name, newSymb);
        return newSymb;
    }

    public static class Symbol {
        // Constructor is private, so that Symbol instances can only be created
        // through SymbolTable.create factory (which thus ensures uniqueness
        // of symbols).
        private Symbol(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        private String name;

        @Override
        public boolean equals(Object other) {
            if (other instanceof Symbol) {
                return this.name.equals(((Symbol) other).name);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
