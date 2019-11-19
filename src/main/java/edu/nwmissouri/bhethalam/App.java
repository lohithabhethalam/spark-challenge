package edu.nwmissouri.bhethalam;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Comparator;
import org.apache.commons.io.FileUtils;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }
    private static void wordCount(String fileName) {
        // define a spark configuration
    // setMaster to local and setAppName to Challenge
    SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("Challenge");

    // define a new JavaSparkContext, pass in the spark configuration object
    JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

    // use sparkContext.textFile() to read data into RDD given the fileName provided
    JavaRDD<String> inputFile = sparkContext.textFile(fileName);

    // use Java API to flatMap text into an RDD of strings (words)
    // In 2.0, FlatMapFunction.call() returns an Iterator rather than Iterable.
    // replace what must this be with the correct variable name
    JavaRDD<String> wordsFromFile = inputFile.flatMap( line -> Arrays.asList(line.split(" ")).iterator());

    // use wordsFromFile and mapToPair to create a new Tuple2 for each word
    // the tuple is in the form (word, 1)
    // Replace each T below to create a JavaPairRDD with the associated types
    JavaPairRDD<String, Integer> countData = wordsFromFile.mapToPair(word -> new Tuple2(word, 1))
        .reduceByKey((x, y) -> (int) x + (int) y);

    // create new JavaPairRDD that reverses the tuple to (n, word)
    // use countData.mapToPair and provide a simple function that
    // takes each pair p and outputs a new Tuple2 with p._2 first, then p._1.
    // then call .sortByKey() to sort in reverse order,
    // pass in Comparator.reverseOrder() to the sortByKey method
    JavaPairRDD<Integer, String> output = countData.mapToPair(p -> new Tuple2(p._2,p._1)).sortByKey(Comparator.reverseOrder());

    // save results to a folder (RDDs are complex) - provide a simple string value.
    String outputFolder = "output.txt";

    // get the path to your outputFolder
    Path path = FileSystems.getDefault().getPath(outputFolder);

    // use FileUtils to delete them quietly
    // It does not work.
    // google and find the import statement required. Add it to this file.
    FileUtils.deleteQuietly(path.toFile());

    // use call saveAsTextFile on your output JavaPairRDD to save your results.
    // Pass in the variable holding your simple string
    //add code here
    output.saveAsTextFile("outputFolder");
    // close your spark context
    //add code here
    
    }
    public static void main( String[] args )
    {

        if (args.length == 0) {
            System.out.println("No files provided.");
            System.exit(0);
        }

        wordCount(args[0]);
    }
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    // public static void main(String[] args) {
    //     System.out.println("Hello World!");
    // }
}
