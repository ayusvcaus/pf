package com.turnitin.question;

import java.util.Collections;
import java.util.List;

/*
 * This class is for Question 2;
 */

public class UniquePhrasesFrequencies {

    private List<String> phrases;
    
    private List<Long> fqs;
  
    public UniquePhrasesFrequencies(List<String> phrases, List<Long> fqs) {
     
       this.phrases = phrases;
       Collections.sort(this.phrases);
       this.fqs = fqs;
       Collections.sort(this.fqs);
    }
   
    public List<String> getPhrases() {
        return phrases;
    }
   
    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }
   
    public List<Long> getFqs() {
        return fqs;
    }
   
    public void setFq(List<Long> fq) {
        this.fqs = fqs;
    }
   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((phrases == null) ? 0 : phrases.hashCode());
		result = prime * result + ((fqs == null) ? 0 : fqs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UniquePhrasesFrequencies)) {
			return false;
		}
		UniquePhrasesFrequencies other = (UniquePhrasesFrequencies) obj;
		if (phrases == null) {
			if (other.getPhrases() != null) {
				return false;
			}
		} else {
			List<String> otherPhrases = other.getPhrases();
			if (otherPhrases == null) {
			    return false;
			}
			if (phrases.isEmpty() && otherPhrases.isEmpty()){  
			    return true;  
			}  
			if (phrases.size() != otherPhrases.size()){  
			    return false;  
			} 
			if (phrases.containsAll(otherPhrases) && otherPhrases.containsAll(phrases)) {
			    for (int i=0; i<phrases.size(); i++) {
			    	String p = phrases.get(i);
			    	String o = otherPhrases.get(i);
			    	if (!p.equals(o)) {
			    		return false;
			    	}
			    }
			    return true;
			}
			return false;
		}		

		if (fqs == null) {
			if (other.getFqs() != null) {
				return false;
			}
		} else {
			List<Long> otherFqs = other.getFqs();
			if (otherFqs == null) {
			    return false;
			}
			if (fqs.isEmpty() && otherFqs.isEmpty()){  
			    return true;  
			}  
			if (fqs.size() != otherFqs.size()){  
			    return false;  
			} 
			if (fqs.containsAll(otherFqs) && otherFqs.containsAll(fqs)) {
			    for (int i=0; i<fqs.size(); i++) {
			    	Long f = fqs.get(i);
			    	Long o = otherFqs.get(i);
			    	if (!f.equals(o)) {
			    		return false;
			    	}
			    }
			    return true;
			}
		}
		return false;
	} 

}
