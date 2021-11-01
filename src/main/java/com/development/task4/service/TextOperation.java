package com.development.task4.service;

import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextOperation {
    List<TextComponent> sortParagraphsBySentenceAmount(TextComposite textComposite);
    List<TextComponent> findLongestWordSentences(TextComposite textComposite);
    List<TextComponent> deleteSentences(TextComposite textComposite, int minWordAmount);
    Map<String, Long> countSimilarWords(TextComposite textComposite);
    long countConsonants(TextComponent sentenceComponent);
    long countVowels(TextComponent sentenceComponent);
}
