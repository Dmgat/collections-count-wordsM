package com.epam.rd.autotasks;

import java.util.*;

public class Words {
    Map<String, Integer> map = new HashMap<>();

    public String countWords(List<String> lines) {
        for (String line : lines) {
            String[] words = line.split("[ .,‘’(“—/:?!”;*)'\"-]|\\s+]");

            for (String word : words) {
                word = word.toLowerCase();
                if (word.length() > 3 && map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else if (word.length() > 3) {
                    map.put(word, 1);
                }
            }
        }

        Map<String, Integer> mapCandidates = new TreeMap<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 9) {
                mapCandidates.put(entry.getKey(), entry.getValue());
            }
        }

        Map<String, Integer> sortedMap = sortByValue(mapCandidates);
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        sb.delete(sb.length() - 1, sb.length());
        return String.valueOf(sb);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {
        List<Map.Entry<K, V>> list = new LinkedList<>(unsortMap.entrySet());
        list.sort(new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                Integer x1 = (Integer) o1.getValue();
                Integer x2 = (Integer) o2.getValue();
                int compareByValue = x2.compareTo(x1);

                if (compareByValue != 0) return compareByValue;

                String s1 = (String) o1.getKey();
                String s2 = (String) o2.getKey();
                return s1.compareTo(s2);
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
