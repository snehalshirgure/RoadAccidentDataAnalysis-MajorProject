//Mapper function :
import java.io.IOException;
import java.util.Vector;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
public class RMapper extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable>{
//Function genrate Candidate itemset using Frequent itemset
Vector< Vector<String> > Gen_Candidate(Vector< Vector<String> > Frequent){
Vector< Vector<String> >Candidate=new Vector< Vector<String> >();
//check (size -1) element of ith and jth freqent itemset match or not and last one different on not
for(int i=0;i<Frequent.size();i++){
for(int j=i+1;j<Frequent.size();j++){
boolean ismatch=true;
int size=Frequent.get(i).size();
for(int k=0;k<size-1;k++)if(!Frequent.get(i).get(k).equals(Frequent.get(j).get(k)))ismatch=false;
if(Frequent.get(i).get(size-1).equals(Frequent.get(j).get(size-1)))ismatch=false;
//if match is found then then create a new candidate item by taking union of ith and jth frequent itemsets
if(ismatch){
Vector<String>item=new Vector<String>();
for(int k=0;k<size-1;k++)item.add(Frequent.get(i).get(k));
item.add(Frequent.get(i).get(size-1));
item.add(Frequent.get(j).get(size-1));
Candidate.add(item);
}
}
}
return Candidate;
}

//check element of item are present in database or not
boolean match(Vector<String>item,Vector<String>Database){
for(int i=0;i<item.size();i++){
boolean ismatch=false;
for(int j=0;j<Database.size();j++)if(item.get(i).equals(Database.get(j)))ismatch=true;
if(ismatch==false)return false;
}
return true;
}
//preprocces database items
Vector<String> Preprocessor(String str){
Vector<String>Database=new Vector<String>();
Vector<String>Prefix=new Vector<String>();
//this short name of used attribute for appending with attribute vakue
String pre="DATA,LOEO,LONO,DATA,DATA,DATA,ACCS,NOVC,NOCS,DATE,DOWK,TIME,LADT,LAHW,DATA,DATA,RTPE,SPLT,JNCD,JNCC,DATA,DATA,DATA,DATA,LGTC,WTRC,RDSC,SCAS,CWHD,UORA,DATA,DATA";
for(String word:pre.split(","))if(word.length()>0)Prefix.add(new String(word));
int k=0;
for(String word:str.split(",")){
if(word.length()>0&&k<Prefix.size()){
String atr=Prefix.get(k++)+"("+word+")";
Database.add(atr);
}
}
return Database;
}
@Override
public void map(LongWritable key, Text value,OutputCollector<Text, IntWritable> output, Reporter r)throws IOException {
//Datastructure for storing frequent item sets of aproiri algorithm
Vector<Vector<String>>Frequent=new Vector<Vector<String>>();
//Datastructure for storing candidate item sets of aproiri algorithm
Vector<Vector<String>>Candidate=new Vector<Vector<String>>();
//Datastructure for storing database which is passed to that map function
Vector<String>Database=new Vector<String>();
String str=value.toString();
Database=Preprocessor(str);
//Initial frequent item which appear major time in attribute coloumn
String Freq="LOEO(198460),LONO(894000),ACCS(3),NOVC(2),NOCS(1),DATE(18/01/1979)

,DOWK(5),TIME(8:00),LADT(11),LAHW(9999),RTPE(1),SPLT(30),JNCD(1),JNCC(4),LGTC(1),WTRC(8),RDSC(1),SCAS(-1),CWHD(0),UORA(-1)";
//split intial frequent item and store in Frequent Item datastructure
for(String word:Freq.split(",")){
Vector<String>item=new Vector<String>();
item.add(new String(word));
Frequent.add(item);
}
//approiri algorithm
while(!Frequent.isEmpty()){
Candidate.clear();
//Generate candidate item using Frequent Itemsets passed to it
Candidate=Gen_Candidate(Frequent);
Frequent.clear();
//check if candidate item present in datastructure or not if present then this is the frequent item
for(int i=0;i<Candidate.size();i++){
if(match(Candidate.get(i),Database))Frequent.add(Candidate.get(i));
}
//convert every frequent itemsets into string and send it to reducer
for(int i=0;i<Frequent.size();i++){
String pattern="{";
for(int j=0;j<Frequent.get(i).size();j++)pattern=pattern+" "+Frequent.get(i).get(j);
pattern=pattern+" }";
output.collect(new Text(pattern), new IntWritable(1));
}
}
}
}
