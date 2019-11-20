# spark-challenge

## Prerequisites

- VS Code
- VS Code Extension: Maven for Java
- VS Code Extension: Java Extension Pack

## Instructions 1 - Start New Maven Project

1. Create a new project using Maven (you do not need to fork this repo).
1. There should be a folder on your laptop where you keep your git projects. Go to this folder, e.g. 44517.
1. Open this parent folder in VS Code.
1. Click the VS Code Extensions icon. Verify you have the two required extensions. If not, install them.
1. Click the VS Code Explorer icon. From the menu, select:
1. View / Command Palette / Maven: Create Maven Project / archetype-quickstart-jdk8 / most recent version.
1. When the folder window opens, click your parent folder up at the top, click "Select Destination Folder".

## Instructions 2 - Interactive Mode

```Bash
groupId: edu.nwmissouri.bhethalam
artifactId: spark-challenge
version: HIT ENTER
package: HIT ENTER
Y: HIT ENTER
```

You will now have a spark-challenge project folder. Exit VS Code.

## Instructions 3 - Code the project

Change directory into your new spark-challenge folder. Right-click and open your spark-challenge folder in VS Code.

Add a basic README.md. Use it to store your notes and commands.

When VS Code asks: "A build file was modified. Do you want to synchronize the Java classpath/configuration?" Answer "Always" to allow VS Code to generate these artifacts automatically.

## Instructions 4 - Add to POM.xml

Copy the POM.xml from this repo to yours. Use CTRL-F to search for "bhethalam".
Change each occurance to match yourname in your groupId instead.

## Prepare the Code

```PowerShell
mvn clean
mvn compile
mvn assembly:single
```

## Execute

```Bash
java -cp target/spark-challenge-1.0.0-jar-with-dependencies.jar edu.nwmissouri.bhethalam.App "input.txt"
```


```Java
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
```

## Challenges - create a process method

Create a new private static void process method that takes one argment, a String containting the fileName (provided in the args).

```Java

    private static void wordCount(String fileName) {
        
    SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("Challenge");

    
    JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

    
    JavaRDD<String> inputFile = sparkContext.textFile(fileName);

    
    JavaRDD<String> wordsFromFile = inputFile.flatMap( line -> Arrays.asList(line.split(" ")).iterator());

    
    JavaPairRDD<String, Integer> countData = wordsFromFile.mapToPair(word -> new Tuple2(word, 1))
        .reduceByKey((x, y) -> (int) x + (int) y);

    
    JavaPairRDD<Integer, String> output = countData.mapToPair(p -> new Tuple2(p._2,p._1)).sortByKey(Comparator.reverseOrder());

    
    String outputFolder = "output.txt";

    
    Path path = FileSystems.getDefault().getPath(outputFolder);

    
    FileUtils.deleteQuietly(path.toFile());

    
    
    output.saveAsTextFile("outputFolder");
   
    
```
## Output
```Text

(12,and)
(11,the)
(11,you)
(7,to)
(6,of)
(5,can)
(5,words)
(5,word)
(5,a)
(4,or)
(4,)
(3,for)
(3,make)
(3,your)
(3,as)
(3,an)
(3,keywords)
(3,help)
(3,text)
(3,from)
(3,will)
(2,This)
(2,if)
(2,be)
(2,our)
(2,into)
(2,editor)
(2,count)
(2,check)
(2,while)
(2,certain)
(2,in)
(2,online)
(2,In)
(2,WordCounter)
(2,sure)
(2,see)
(2,writing.)
(1,plagiarism.)
(1,density)
(1,For)
(1,back)
(1,this)
(1,grammar)
(1,it)
(1,The)
(1,reading)
(1,its)
(1,reaches)
(1,writing)
(1,Bookmark)
(1,improve)
(1,leave)
(1,order)
(1,we)
(1,above.)
(1,Knowing)
(1,which)
(1,place)
(1,also)
(1,over)
(1,now.)
(1,detect)
(1,To)
(1,any)
(1,requirement)
(1,what)
(1,time)
(1,minimum)
(1,amount)
(1,paste)
(1,optionally,)
(1,Details)
(1,author)
(1,article)
(1,addition,)
(1,important.)
(1,you're)
(1,cursor)
(1,site)
(1,editing,)
(1,prevent)
(1,how)
(1,them.)
(1,type,)
(1,Reading)
(1,you’re)
(1,indicator)
(1,average)
(1,so.)
(1,style,)
(1,book,)
(1,but)
(1,delete,)
(1,program)
(1,Level)
(1,combinations)
(1,Auto-Save)
(1,distribution)
(1,is)
(1,box)
(1,level)
(1,person)
(1,it.)
(1,number)
(1,You)
(1,even)
(1,simply)
(1,You'll)
(1,would)
(1,best)
(1,possible)
(1,article,)
(1,come)
(1,at)
(1,specific)
(1,limit.)
(1,copy)
(1,top)
(1,typing.)
(1,We)
(1,strive)
(1,feature)
(1,increase)
(1,keyword)
(1,won't)
(1,shows)
(1,lose)
(1,has)
(1,page)
(1,mistakes)
(1,10)
(1,and,)
(1,use)
(1,allows)
(1,later.)
(1,within)
(1,education)
(1,overview)
(1,start)
(1,maximum)
(1,cannot)
(1,name)
(1,another)
(1,understand)
(1,Apart)
(1,report,)
(1,edit)
(1,stays)
(1,above)
(1,need)
(1,characters,)
(1,know)
(1,paper,)
(1,choice)
(1,Disclaimer:)
(1,text,)
(1,tools)
(1,count,)
(1,using.)
(1,essay,)
(1,Tip:)
(1,accurate)
(1,story,)
(1,always)
(1,often)
(1,example,)
(1,changes)
(1,characters)
(1,decrease)
(1,counting)
(1,speaking)
(1,percentages.)
(1,guarantee)
(1,write)
(1,over-using)

```

## Reference

- <https://github.com/denisecase/spark-challenge>


