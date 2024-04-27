package cs2.sampler;
import java.util.*;

public class SamplerTester {
  public static void main(String[] args) {
    new SamplerTester().run("samplerTestData.tsv");
  }
  public void run(String filename) {
    Sampler data = new Sampler(filename);
    for (String s : data.getWords()){
      System.out.println(s + data.getProbability(s));
    }
    Sampler test = new Sampler();
    for(int i = 0; i < 1000000; i++) {
      test.increment(data.sample());
    }
    if(test.getWords() == null) {
      System.out.println("getWords() is not implemented");
    } else {
      ArrayList<String> words = new ArrayList<String>(test.getWords());
      System.out.println(words.size());
      Collections.sort(words);
      double epsilon = 0.001;
      for (String word : words) {
        System.out.println("HHH");
        double frequenceDifference = Math.abs(test.getProbability(word) - data.getProbability(word));
        if (frequenceDifference > epsilon) {
          System.out.println(word + " " + test.getProbability(word) + " " + data.getProbability(word));
        } else {
          System.out.println(word + "\tfrequency is within " + epsilon + " of " + String.format("%.04f", data.getProbability(word)));
        }
      }
    }
  }
}
