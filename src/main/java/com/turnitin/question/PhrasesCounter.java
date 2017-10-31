package com.turnitin.question;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import  com.turnitin.question.PhraseFrequency;
import  com.turnitin.question.UniquePhrasesFrequencies;

public class PhrasesCounter {
    public static Map<String, List<String>> PHRASES = new HashMap<>();

    static {
        PHRASES.put("1", Arrays.asList("afterward", "whenever", "however", "until", "as soon as", "as long as", "while", "again", "also", "besides"));
        PHRASES.put("2", Arrays.asList("therefore", "thus", "consequently", "as a result", "for this reason", "so", "so that", "accordingly", "because"));
        PHRASES.put("3", Arrays.asList("in addition to", "so", "moreover"));
        PHRASES.put("4", Arrays.asList("in general", "for the most part", "as a general rule", "on the whole", "usually", "typically", "in summary"));
    }
   
    public static String text = "Afterward, a yellow bird landed on the tall tree in addition to a lazy tortoise.  " +
           "However, he had a red beak. In addition to white the patches on the wings, he was completely yellow. " +
           "In summary, it was yellow bird. In summary, it did not sing.";

  
    public static Map<String, List<PhraseFrequency>> phraseFrequencyListMap;
    public static Map<String, UniquePhrasesFrequencies> uniquePhrasesFrequenciesMap;

    /*
     * This method is for Question 1 possibly;
     */
    public static List<PhraseFrequency> getPhraseFrequencieList(List<String> delimiters, Supplier<Stream<String>> streamSupplier) throws Exception {
    	Collections.sort(delimiters);
        List< PhraseFrequency> list = new ArrayList< PhraseFrequency>();
        delimiters.forEach(delimiter->{
        	try {
        	    long cnt=getPhraseFrequency(delimiter, streamSupplier); 
        	    list.add(new PhraseFrequency(delimiter, cnt)); 
        	} catch (Exception e) {
        		System.out.println(e.toString());
        	}
        });    
        return list;
    }
 
    /*
     * This method is for Question 2;
     */
    public static UniquePhrasesFrequencies getUniquePhrasesFrequencies(List<String> delimiters, Supplier<Stream<String>> streamSupplier) throws Exception {
    
        List<Long> vacabularyFrequencies = new ArrayList<Long>();
        
        List<String> noDuplicate = delimiters.stream().distinct().collect(Collectors.toList());
        
        noDuplicate.forEach(delimiter->{
        	try {
        		long cnt=getPhraseFrequency(delimiter, streamSupplier); 
        		vacabularyFrequencies.add(cnt); 
        	} catch (Exception e) {
        		System.out.println(e.toString());
        	}      	
         });    
        return new UniquePhrasesFrequencies(noDuplicate, vacabularyFrequencies);
    }
    
    /*
     * This method is for Question 1 possibly;
     */
    public static String getPhrasesFrequenciesString(List<String> delimiters, Supplier<Stream<String>> streamSupplier) throws Exception {
    	
    	Collections.sort(delimiters);
    	Map<String, Long> map = new LinkedHashMap();   
        delimiters.forEach(delimiter->{ 
        	try {
        		long cnt=getPhraseFrequency(delimiter, streamSupplier); 
        		map.put(delimiter, cnt); 
        	} catch (Exception e) {
        		System.out.println(e.toString());
        	}      	
        });     
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Long>>() {}.getType();
        String s = gson.toJson(map, type);
        return "frequencies: " + s.substring(0, s.length()-1).substring(1);
        
    }
  
    public static long getPhraseFrequency(String delimiter, Supplier<Stream<String>> streamSupplier) throws Exception {
        return streamSupplier.get().map(String::toLowerCase).map(w-> w.split(delimiter)).flatMap(Arrays::stream).count()-1;   
    }
   
    public static void main(String[] args) throws Exception {

        Supplier<Stream<String>> streamSupplier = () ->Arrays.asList(text).stream();
   
        phraseFrequencyListMap = new HashMap<String, List<PhraseFrequency>>();
        uniquePhrasesFrequenciesMap = new HashMap<String, UniquePhrasesFrequencies>();
        PHRASES.keySet().forEach(key->{
        	try {
        	    List<String> ls = PHRASES.get(key);                         
                phraseFrequencyListMap.put(key, getPhraseFrequencieList(ls, streamSupplier));
                uniquePhrasesFrequenciesMap.put(key, getUniquePhrasesFrequencies(ls, streamSupplier));
        	} catch(Exception e) {
                System.out.println(e.toString());
        	}
        });       
    }

}
