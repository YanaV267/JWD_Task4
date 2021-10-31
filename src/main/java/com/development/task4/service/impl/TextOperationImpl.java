package com.development.task4.service.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.service.TextOperation;

import java.util.*;

public class TextOperationImpl implements TextOperation {
    private static final String VOWELS = "(?iu:[aouieyаоэуыеяёюи])";
    private static final String CONSONANTS = "(?iu:[b-zб-ь&&[^еёиоуыeiouy]])";

    @Override
    public List<TextComponent> sortParagraphsBySentenceAmount(TextComposite textComposite) {
        return textComposite.getChildren().stream().sorted(new TextComposite.SentenceAmountComparator()).toList();
    }

    @Override
    public List<TextComponent> findLongestWordSentences(TextComposite textComposite) {
        int maxLength = findLongestWordLength(textComposite);
        return textComposite.getChildren()
                .stream()
                .flatMap(p -> p.getChildren().stream()).toList()
                .stream().filter(s -> s.getChildren()
                        .stream()
                        .anyMatch(l -> l.getType().equals(ComponentType.WORD) && l.toString().length() == maxLength))
                .toList();
    }

    public int findLongestWordLength(TextComposite textComposite) {
        TextComponent textComponent = textComposite.getChildren()
                .stream().flatMap(p -> p.getChildren().stream()).toList()
                .stream().flatMap(s -> s.getChildren().stream()).toList()
                .stream().filter(l -> l.getType().equals(ComponentType.WORD)).toList()
                .stream().max(Comparator.comparingInt(l -> l.toString().length()))
                .get();
        return textComponent.toString().length();
    }

    @Override
    public List<TextComponent> deleteSentences(TextComposite textComposite, int minWordAmount) {
        return textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream()).toList()
                .stream().filter(s -> s.getChildren()
                        .stream()
                        .filter(l -> l.getType().equals(ComponentType.WORD)).count() >= minWordAmount).toList();
    }

    @Override
    public Map<String, Integer> countSimilarWords(TextComposite textComposite) {
        Map<String, Integer> similarWords = new HashMap<>();
        List<TextComponent> paragraphs = textComposite.getChildren();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChildren();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChildren();
                for (TextComponent lexeme : lexemes) {
                    if (lexeme.getType().equals(ComponentType.WORD)) {
                        String word = lexeme.toString().toLowerCase();
                        int repetitionAmount = similarWords.get(word) != null ? similarWords.get(word) + 1 : 1;
                        similarWords.put(word, repetitionAmount);
                    }
                }
            }
        }
        similarWords.entrySet().removeIf(w -> w.getValue() == 1);
        return similarWords;
    }

    @Override
    public long countConsonants(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren()
                .stream().flatMap(s -> s.getChildren().stream()).toList()
                .stream().flatMap(l -> l.getChildren().stream()).toList()
                .stream().flatMap(w -> w.getChildren().stream()).toList()
                .stream().filter(l -> l.toString().matches(CONSONANTS))
                .count();
    }

    @Override
    public long countVowels(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren()
                .stream().flatMap(s -> s.getChildren().stream()).toList()
                .stream().flatMap(l -> l.getChildren().stream()).toList()
                .stream().flatMap(w -> w.getChildren().stream()).toList()
                .stream().filter(l -> l.toString().matches(VOWELS))
                .count();
    }
}
