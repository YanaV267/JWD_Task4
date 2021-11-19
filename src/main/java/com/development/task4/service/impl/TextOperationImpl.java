package com.development.task4.service.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.service.TextOperation;

import java.util.*;
import java.util.stream.Collectors;

public class TextOperationImpl implements TextOperation {
    private static final String VOWELS = "(?iu:[aouieyаоэуыеяёюи])";
    private static final String CONSONANTS = "(?iu:[b-zб-ь&&[^еёиоуыeiouy]])";

    @Override
    public List<TextComponent> sortParagraphsBySentenceAmount(TextComposite textComposite) {
        return textComposite.getChildren().stream()
                .sorted(new TextComposite.SentenceAmountComparator())
                .toList();
    }

    @Override
    public List<TextComponent> findLongestWordSentences(TextComposite textComposite) {
        int maxLength = findLongestWordLength(textComposite);
        return textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .filter(l -> l.getChildren().stream()
                        .anyMatch(w -> w.getType().equals(ComponentType.WORD) && w.toString().length() == maxLength))
                .toList();
    }

    public int findLongestWordLength(TextComposite textComposite) {
        TextComponent textComponent = textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .max(Comparator.comparingInt(w -> w.toString().length()))
                .get();
        return textComponent.toString().length();
    }

    @Override
    public List<TextComponent> deleteSentences(TextComposite textComposite, int minWordAmount) {
        return textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .filter(s -> s.getChildren().stream()
                        .flatMap(l -> l.getChildren().stream())
                        .filter(w -> w.getType().equals(ComponentType.WORD)).count() >= minWordAmount).toList();
    }

    @Override
    public Map<String, Long> countSimilarWords(TextComposite textComposite) {
        Map<String, Long> similarWords = textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .collect(Collectors.groupingBy(w -> w.toString().toLowerCase(), Collectors.counting()));
        similarWords.entrySet().removeIf(w -> w.getValue() == 1);
        return similarWords;
    }

    @Override
    public long countConsonants(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren().stream()
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.toString().matches(CONSONANTS))
                .count();
    }

    @Override
    public long countVowels(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren().stream()
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.toString().matches(VOWELS))
                .count();
    }
}
