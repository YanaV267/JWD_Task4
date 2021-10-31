package com.development.task4.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolLeaf implements TextComponent {
    private static final Logger LOGGER = LogManager.getLogger();
    private ComponentType componentType;
    private char symbol;

    public SymbolLeaf() {

    }

    public SymbolLeaf(ComponentType componentType, char symbol) {
        this.symbol = symbol;
        this.componentType = componentType;
    }

    @Override
    public void add(TextComponent textComponent) {
        LOGGER.error("Unsupported operation \"add\" on symbol");
        throw new UnsupportedOperationException("Unsupported operation \"add\" on symbol");
    }

    @Override
    public void remove(TextComponent textComponent) {
        LOGGER.error("Unsupported operation \"remove\" on symbol");
        throw new UnsupportedOperationException("Unsupported operation \"remove\" on symbol");
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public List<TextComponent> getChildren() {
        LOGGER.error("Unsupported operation of getting children on symbol");
        throw new UnsupportedOperationException("Unsupported operation of getting children on symbol");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SymbolLeaf that = (SymbolLeaf) o;
        if (symbol != that.symbol)
            return false;
        return componentType != null ? componentType == that.componentType : that.componentType == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (componentType != null ? componentType.hashCode() : 0);
        result = 31 * result + Character.hashCode(symbol);
        return result;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
