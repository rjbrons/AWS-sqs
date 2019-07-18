/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        publish();
        listen();
    }


    public static void publish(){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl("https://sqs.us-east-2.amazonaws.com/489441999243/QueueA")
                .withMessageBody("hello world")
                .withDelaySeconds(5);
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        sqs.sendMessage(send_msg_request);
    }

    public static void listen(){
        AmazonSQS receiver = AmazonSQSClientBuilder.defaultClient();
        List<Message> messages = receiver.receiveMessage("https://sqs.us-east-2.amazonaws.com/489441999243/QueueA").getMessages();
        for (Message m : messages){
            System.out.println(m.getBody());
        }
    }
}
