# Test_task_for_Media_Praha_s.r.o

This application operates on the principle of sending object code via URL request parameters. It would have been possible to do this using the POST method and embedding the code in the request body, but the task specifies doing it through the URL. Then, an initial check is made for the presence and length of this code. Subsequently, the code undergoes primary processing and is divided into parts. Each part is individually checked for compliance with the code format, and if everything is sent correctly, the object is added to an ArrayList. If not, a null is added to the ArrayList, and then when iterating through this ArrayList, if a null object is detected, a BAD_REQUEST response is immediately sent. If everything is fine, each object is saved to the database. In a simple test file, I am testing the functionality of storing the objects Data - výpis v Kč, Data – obratová položka v Kč, as well as these two codes together.
