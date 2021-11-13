package com.development.task4.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents = new ArrayList<>();
    private ComponentType componentType;

    public TextComposite() {

    }

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public List<TextComponent> getChildren() {
        return List.copyOf(textComponents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return (textComponents != null ? textComponents.equals(that.textComponents) : that.textComponents == null) &&
                (componentType != null ? componentType == that.componentType : that.componentType == null);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + (textComponents != null ? textComponents.hashCode() : 0);
        result = result * 31 + (componentType != null ? componentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = componentType.getDelimiter();
        if (componentType.equals(ComponentType.PARAGRAPH)) {
            sb.append(delimiter);
        }
        for (TextComponent textComponent : textComponents) {
            sb.append(textComponent.toString());
        }
        if (!componentType.equals(ComponentType.PARAGRAPH)) {
            sb.append(delimiter);
        }
        return sb.toString();
    }

    public static class SentenceAmountComparator implements Comparator<TextComponent> {

        @Override
        public int compare(TextComponent textComponent1, TextComponent textComponent2) {
            return Integer.compare(textComponent1.getChildren().size(), textComponent2.getChildren().size());
        }
    }
}
