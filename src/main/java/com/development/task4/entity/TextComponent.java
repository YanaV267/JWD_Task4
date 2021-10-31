package com.development.task4.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent textComponent);
    void remove(TextComponent textComponent);
    ComponentType getType();
    List<TextComponent> getChildren();
}
