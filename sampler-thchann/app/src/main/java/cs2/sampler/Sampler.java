package cs2.sampler;

import java.io.File;
import java.rmi.StubNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.Random; 

import com.google.common.io.LittleEndianDataInputStream;

public class Sampler {

  private double val = 0;
  private int sum;
  Map<String, Integer> map = new HashMap<>();
  String[] split;

  /* Constructors */
  public Sampler() {}
  
  public Sampler(String filename) {
      try {
      File file = new File(filename);
      Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
          String line = scan.nextLine();
          this.split = line.split("\\s");
          int cast = Integer.parseInt(split[1]);
          map.put(split[0], cast);
        }
    } catch (Exception e){
      System.out.println("Error");
    }
    System.out.println(map);
  }


  /* Methods */
  //You will need to implement the following methods and change their return values
  public Set<String> getWords() { 
    return map.keySet();
  }

  public int getCount(String word) { 
    if (!map.containsKey(word)){
      return 0;
    } else {
      return map.get(word); 
    }
  }

  public int totalCount() { 
    this.sum = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()){
      this.sum += entry.getValue();
    }
    return this.sum;
  }

  public double getProbability(String word) { 
    int vals = totalCount();
    System.out.println(vals);
    if (!map.containsKey(word)){
      return 0.0;
    };
    return (double)map.get(word)/vals;
  }

  public void increment(String word) {
    if (!map.containsKey(word)){
      map.put(word, 1);
    } else {
      map.put(word, map.get(word) + 1);
    }
  }

  public String sample() { 
    ArrayList<String> list = new ArrayList<String>();
    for (Map.Entry<String, Integer> entry : map.entrySet()){
      //entry.setValue(entry.getValue());
      for (int i = 0; i != entry.getValue(); i++){
        list.add(entry.getKey());
      }
    }
    
    Random rand = new Random();
    int randomNum = rand.nextInt(list.size());
    String randomWord = list.get(randomNum);
    //System.out.println(randomWord);
    return randomWord; 
  }
}
