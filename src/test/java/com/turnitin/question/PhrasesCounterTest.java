package com.turnitin.question;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;

import com.turnitin.question.PhrasesCounter;
import com.turnitin.question.PhraseFrequency;
import com.turnitin.question.UniquePhrasesFrequencies;

public class PhrasesCounterTest {
	
	public static Supplier<Stream<String>> streamSupplier;
	
	@BeforeClass
	public static void setup() {
		streamSupplier = () ->Arrays.asList(PhrasesCounter.text).stream();
	}
	
	@Test
	public void testGetPhraseFrequencieList() throws Exception {
		List<PhraseFrequency> expected = new ArrayList<PhraseFrequency>();
		PhraseFrequency pf = new PhraseFrequency("afterward", 1);
		expected.add(pf);
		pf = new PhraseFrequency("whenever", 0);
		expected.add(pf);
		pf = new PhraseFrequency("however", 1);
		expected.add(pf);
		pf = new PhraseFrequency("until", 0);
		expected.add(pf);
		pf = new PhraseFrequency("as soon as", 0);
		expected.add(pf);
		pf = new PhraseFrequency("as long as", 0);
		expected.add(pf);
		pf = new PhraseFrequency("again", 0);
		expected.add(pf);
		pf = new PhraseFrequency("also", 0);
		expected.add(pf);
		pf = new PhraseFrequency("besides", 0);
		expected.add(pf);
		Collections.sort(expected);

		List<PhraseFrequency> actual = PhrasesCounter.getPhraseFrequencieList(PhrasesCounter.PHRASES.get("1"), streamSupplier);
		Collections.sort(actual);
		
		for (int i=0; i<expected.size(); i++) {
			PhraseFrequency pfe = expected.get(i);
			PhraseFrequency pfa = actual.get(i);
			assertEquals(pfe, pfa);
		}
	}
	
	@Test
	public void testGetUniquePhrasesFrequencies() throws Exception {
		List<String> expPhrases = PhrasesCounter.PHRASES.get("4");
		Long[] expResults = {0l,0l,0l,0l,0l,0l,2l};
		List<Long> expFrequencies = Arrays.asList(expResults);
		UniquePhrasesFrequencies expected = new UniquePhrasesFrequencies(expPhrases, expFrequencies);
		
		List<String> oneDuplicate = new ArrayList<String>(expPhrases);
		oneDuplicate.add(expPhrases.get(2));

		UniquePhrasesFrequencies actual = PhrasesCounter.getUniquePhrasesFrequencies(oneDuplicate, streamSupplier);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetPhrasesFrequenciesString() throws Exception {
		
		String expected = "frequencies: \"as a general rule\":0,\"for the most part\":0,\"in general\":0,\"in summary\":2,\"on the whole\":0,\"typically\":0,\"usually\":0";	
        String actual = PhrasesCounter.getPhrasesFrequenciesString(PhrasesCounter.PHRASES.get("4"), streamSupplier);
        assertEquals(expected, actual);
	}
}
