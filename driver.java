//Driver function:
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class RDriver extends Configured implements Tool {
@Override
public int run(String[] args) throws Exception {
if(args.length<2){
System.out.println("Give proper input and output directory");
return -1;
}
JobConf conf=new JobConf(RDriver.class);
//Set input and output path from taking input and providing out on hdfs
FileInputFormat.setInputPaths(conf, new Path(args[0]));

FileOutputFormat.setOutputPath(conf, new Path(args[1]));
//set mapper and reducer class for execution in map phase and reduce phase
conf.setMapperClass(RMapper.class);
conf.setReducerClass(RReducer.class);
//set mapper output key and value type
conf.setMapOutputKeyClass(Text.class);
conf.setMapOutputValueClass(IntWritable.class);
//set final output key and value type
conf.setOutputKeyClass(Text.class);
conf.setOutputValueClass(IntWritable.class);
//submit the job
JobClient.runJob(conf);
return 0;
}
public static void main(String[] args) throws Exception{
int exitcode=ToolRunner.run(new RDriver(), args);
System.out.println(exitcode);
}
}