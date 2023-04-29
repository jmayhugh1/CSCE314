
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 6 Problem 3
   First, study how this class should work with the test code in SimMain.java
   carefully!

   Student Name: Joshua Mayhugh
   Student UIN: 431004527
   Acknowledgements:
*/

import java.util.*;

class SimBox implements Runnable {
  static final int MAX_SIZE = 10;

  class Message {
    String sender;
    String recipient;
    String msg;
    Message(String sender, String recipient, String msg) {
      this.sender = sender;
      this.recipient = recipient;
      this.msg = msg;
    }
  }

  private final LinkedList<Message> messages;
  private LinkedList<Message> myMessages;
  private String myId;
  private boolean stop = false;

  public SimBox(String myId) {
    messages = new LinkedList<Message>();
    this.myId = myId;
    this.myMessages = new LinkedList<Message>();
    new Thread(this).start();
  }

  public SimBox(String myId, SimBox s) {
    this.messages = s.messages;
    this.myId = myId;
    this.myMessages = new LinkedList<Message>();
    new Thread(this).start();
  }

  public String getId() { return myId; }

  public void stop() {
    // make it so that this Runnable will stop
    stop = true;
  }

  public void send(String recipient, String msg) {
    // add a message to the shared message queue (messages)
    synchronized(messages)
    {
messages.add(new Message(myId, recipient, msg));
    // you will have to synchronize the message queue
    }


  }

  public List<String> retrieve() {
    // return the contents of myMessages
    synchronized(myMessages)
    {
    List<String> result = new ArrayList<String>();
for(Message m : myMessages){
  result.add("From " + m.sender + " to " + m.recipient + ": " + m.msg); //iterate through myMessages and add each message to result
}
    // and empty myMessages
    myMessages.clear(); // clear the list
    // you will have to synchronize myMessages
    // each message should be in the following format:
    //   From (the sender) to (the recipient) (actual message)

    return result;
}
  }

  public void run() 
  {
  // loop forever
  // 1. Approximately once every second move all messages
  //    addressed to this mailbox from the shared message queue
  //    to the private myMessages queue
  //    To do so, you need to synchronize messages and myMessages.
  //    Furthermore, you need to explicitly use the iterator() of messages
  //    with a while loop.  A for-each loop will not work here.
  // 2. Also approximately once every second, if the message
  //    queue has more than MAX_SIZE messages, delete oldest messages
  //    so that size is at most MAX_SIZE. This part of code is provided
  //    below.

    for(;;) { // loop forever
      //sleep for 1 second
      // synchronize messages and myMessages
      synchronized(messages)
      {
        synchronized(myMessages)
        { 

      // have the iterator of messages referred by iter of
      // type Iterator<Message>
Iterator<Message> iter = messages.iterator();
while(iter.hasNext())
{


      // while there is more to access on iter, access the message
      Message m = iter.next();

      // if the message's recipient is equal to myId, then remove the
      // message from messages and add the message to myMessages
      if(m.recipient.equals(myId))
      {
        iter.remove();
        myMessages.add(m);
      }
}
        }
      
      // end of synchronized myMessages
      while (messages.size() > MAX_SIZE) { messages.removeFirst(); }
      // end of synchronized messages
      }
      if (stop) return;
      try { Thread.sleep(1000); } catch (InterruptedException e) {}
    } // endfor
  } // end run()
} // end SimBox


