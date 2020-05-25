import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

import org.jsoup.Jsoup;


public class QueryProcessor {
	public String[] query;
	public Map<String, wordValue> wordsDictionary;
	public Map<Integer, String> documentsURLs;
	
	public QueryProcessor(String searchQuery, Map<Integer, String> documentsURLs, Map<String, wordValue> wordsDictionary) {
		this.query = splitAndStam(searchQuery);
		this.documentsURLs = documentsURLs;
    	this.wordsDictionary = wordsDictionary;
	}
	
	public String[] splitAndStam(String searchQuery){
		String[] splittedQuery;
		Stemmer stemmer = new Stemmer();
		splittedQuery = searchQuery.split(" ");
		for(int i = 0; i < splittedQuery.length; i++) {
			splittedQuery[i] = stemmer.stemTerm(splittedQuery[i]);
		}
		return splittedQuery;
	}
	
	public void runRanker() {
		Relevance r = new Relevance(this.query);
	}
	
//	public void getDataFromDatabase() {  // from file till now
//		String[] splittedData;
//		// getting documents URL dictionary
//		try {
//		      File myObjDocumentsUrl = new File("documentsURLs.txt");     // open file
//		      Scanner myReaderDocumentsUrl = new Scanner(myObjDocumentsUrl);   // scan file
//		      while (myReaderDocumentsUrl.hasNextLine()) {                     // loop on lines in file
//		        String dataDocumentsUrl = myReaderDocumentsUrl.nextLine();     // read line in file
//		        splittedData = dataDocumentsUrl.split(" ");                    // split this line at every " "
//		        for(int i = 0; i < splittedData.length/2; i++)                 // loop on the array to store data
//		        {
//		        	documentsURLs.put(Integer.parseInt(splittedData[i*2]), splittedData[i*2+1]);
////		        	System.out.println(index + " " + splittedData[i*2+1]);
//		        }
//		      }
//		      myReaderDocumentsUrl.close();
//		    } catch (FileNotFoundException e) {
//		      System.out.println("An error occurred.");
//		      e.printStackTrace();
//		    }
//		
//		// getting word dictionary
//		wordValue w = new wordValue();
//		Map<Integer, List<Float>> tdfDictionary = new LinkedHashMap();
//		List<Float> priorityList = new ArrayList();
//		String word;
//		int priorityListSize;
//		int tempIndex;
//		int counter = 1;
//				try {
//				      File myObjWordsDictionary = new File("wordsDictionary.txt");     // open file
//				      Scanner myReaderWordsDictionary = new Scanner(myObjWordsDictionary);   // scan file
//				      while (myReaderWordsDictionary.hasNextLine()) {                     // loop on lines in file
//				        String dataWordsDictionary = myReaderWordsDictionary.nextLine();     // read line in file
//				        splittedData = dataWordsDictionary.split(" ");                    // split this line at every " "
//				        int numOfWords = Integer.parseInt(splittedData[0]);          // #words in the dictionary
//				        System.out.println("num of words = "+numOfWords+" size = "+splittedData.length);
////				        for(int i = 0; i < numOfWords; i++)                 // loop on the array to store data
////				        {
////				        	word = splittedData[counter++];
////				        	w.idf = Float.parseFloat(splittedData[counter++]);
////				        	priorityListSize = Integer.parseInt(splittedData[counter++]);
////				        	for(int j = 0; j < priorityListSize; j++)
////				        	{
////				        		tempIndex = Integer.parseInt(splittedData[counter++]);
////				        		priorityList.add(Float.parseFloat(splittedData[counter++]));
////				        		priorityList.add(Float.parseFloat(splittedData[counter++]));
////				        		priorityList.add(Float.parseFloat(splittedData[counter++]));
////				        		priorityList.add(Float.parseFloat(splittedData[counter++]));
////				        		tdfDictionary.put(tempIndex, priorityList);
////				        	}
////				        	w.tdfDictionary = tdfDictionary;
////				        	
////				        	wordsDictionary.put(word, w);
//////				        	System.out.println(index + " " + splittedData[i*2+1]);
////				        }
//				      }
//				      myReaderWordsDictionary.close();
//				    } catch (FileNotFoundException e) {
//				      System.out.println("An error occurred.");
//				      e.printStackTrace();
//				    }
//	}

	public static void main(String[] args) throws IOException {
		Indexer indexer;
		indexer  = new Indexer();
    	indexer.getDocumentsURLs();
    	indexer.Indexing(indexer.documentsURLs);
    	wordValue entry;
    	entry = indexer.wordsDictionary.get("significant");
    	
		String s = "test this testing to be tested";
		QueryProcessor qp = new QueryProcessor(s, indexer.documentsURLs, indexer.wordsDictionary);
		String[] q = qp.query;
//		qp.getDataFromDatabase();
		for(int i = 0; i < q.length; i++) {
			System.out.println(q[i]); 
		}
		
		
		
	}
}