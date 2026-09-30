# My design explanation

Draw PrintDesk, ReceiptSink, RecordingReceiptSink and PrintJob.
Add private fields, constructor arguments, interface realization and relevant
associations/multiplicities. A hand drawing plus a text alternative is enough.





Answer in your own words:
1. Which object receives the submit call, and which receives accept?
//the object that receives the accept call is the arraylist so it can add the job to it and the object that recievie the submit call is the PrintDesk object because submit all it does is validate the job so its actually real and not null so then it desk will validate and then accept it to the arraylist so it can be added to the list of jobs. 

2. Which class protects the valid page range?
The class that does that is the PrintJob class as this validates what inside the job is real since you can't create a job without PrintJob class

3. Why is a live unmodifiable view insufficient for an old snapshot?
This is because an old snapshot needs to be updated and if you can't modify it it won't be able to be add or remove things. Additionally, you can't make it completely modifiable because it is not protected, so you have to create a separate list unmodifiable list that is only used for viewing purposes.

4. Why does a constructor-supplied collaborator not imply exclusive ownership?
This is because code from outside is created so that ownership of the object is inside for example sink holds a reference to it so it can be shared with other objects. The sinks lifttime does not depend on the desk so it makes an association rather than composition.


Sketch the message order for one valid submission and explain why invalid input

PrintJob("notblank",[1-10])-> submit(job) -> check job != null -> accept(job) -> receipts.add(job);

if invalid input yoou would be adding a null object into a list or something outside reuqired range

must be rejected before any receipt is added.