package spring.boot.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class S3Application {

    private static final AWSCredentials credentials;
    private static String bucketName;  

    static {
        //put your accesskey and secretkey here
        credentials = new BasicAWSCredentials(
          "<your access key>",
          "<your secrect access id>"
        );
    }
    
    public static void main(String[] args) throws IOException {
        //setup the client
        AmazonS3 s3client = AmazonS3ClientBuilder
          .standard()
          .withCredentials(new AWSStaticCredentialsProvider(credentials))
          .withRegion(Regions.AP_SOUTHEAST_1)
          .build();
        
        AWSS3Service awsService = new AWSS3Service(s3client);

        bucketName = "trainingbucketnd65";

        //creating a bucket
        if(awsService.doesBucketExist(bucketName)) {
            System.out.println("Bucket name is not available."
              + " Try again with a different Bucket name.");
            return;
        }
        awsService.createBucket(bucketName);
        
        //list all the buckets
        for(Bucket s : awsService.listBuckets() ) {
            System.out.println(s.getName());
        }
        
        //deleting bucket
      //  awsService.deleteBucket("baeldung-bucket-test2");
        
        //uploading object
   /*     awsService.putObject(
          bucketName, 
          "Document/hello.txt",
          new File("C:/Users/Nilay/OneDrive/Documents/emails.txt")
        );*/

        //listing objects
        /*for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            System.out.println(os.getKey());
            ObjectListing objectListing = awsService.listObjects(bucketName);
        }*/

        //downloading an object
        /*S3Object s3object = awsService.getObject(bucketName, "Document/hello.txt");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File("C:/Users\\Nilay/OneDrive/Documents/hello.txt"));
        
        *///copying an object
        /*awsService.copyObject(
          "baeldung-bucket", 
          "picture/pic.png", 
          "baeldung-bucket2", 
          "Document/picture.png"
        );*/
        
        //deleting an object
        //awsService.deleteObject(bucketName, "Document/hello.txt");

        //deleting multiple objects
        /*String objkeyArr[] = {
          "Document/hello2.txt", 
          "Document/picture.png"
        };*/
        
        /*DeleteObjectsRequest delObjReq = new DeleteObjectsRequest("baeldung-bucket")
          .withKeys(objkeyArr);
        awsService.deleteObjects(delObjReq);*/
    }
}
