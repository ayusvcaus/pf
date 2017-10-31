package com.turnitin.question;

/*
 * This class is for Question 1 possibly;
 */

public class PhraseFrequency implements Comparable<PhraseFrequency>{

    private String phrase;
    private Long fq;
   
    public PhraseFrequency(String phrase, long fq) {
     
        this.phrase = phrase;
        this.fq = fq;
    }
   
    public String getPhrase() {
        return phrase;
    }
   
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
   
    public Long getFq() {
        return fq;
    }
   
    public void setFq(Long fq) {
        this.fq = fq;
    }
   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((phrase == null) ? 0 : phrase.hashCode());
		result = prime * result + ((fq == null) ? 0 : fq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PhraseFrequency)) {
			return false;
		}
		PhraseFrequency other = (PhraseFrequency) obj;
		if (phrase == null) {
			if (other.getPhrase() != null) {
				return false;
			}
		} else if (!phrase.equals(other.getPhrase())) {
			return false;
		}
		if (fq == null) {
			if (other.getFq() != null) {
				return false;
			}
		} else if (!fq.equals(other.getFq())) {
			return false;
		}
	
		return true;
	} 
	
	@Override
	public int compareTo(PhraseFrequency pf) {
		return phrase.compareTo(pf.getPhrase());
	}
}
