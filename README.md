# BuddyAddressBook
An address book project used to explore some of the following Java concepts such as: 
* Model-View-Controller (MVC) design pattern
* Object-orientated principles (OOP)
* Refactoring 
* Graphic user-interface (GUI) using Swing components
* JUnit4 for unit testing
* Java serialization
* SAX parsing of XML
* Ant build configurations

## Dependencies

* [OpenJDK](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html) v16.0.2 - A development environment for building applications, applets, and components using the Java programming language.
* [JUnit4](https://junit.org/junit4/) - A unit testing framework for the Java programming language.
  - hamcrest-core-1.3
  - junit-4.13.1

## Program Execution

Run the app on Windows Command Prompt.
```
java -jar C:\filepath\BuddyAddressBook.jar 
```

## Application Features
When the program first opens. We see an empty address book.

![main1](https://user-images.githubusercontent.com/55768917/149059669-04b04a2a-16fd-4ded-818a-489aec325fe5.png)

### Create a new Address Book
In order to add a new contact, we first must <strong><em>create</em></strong> a new address book.
Then select the <strong><em>add</em></strong> menu item.

![menu1](https://user-images.githubusercontent.com/55768917/149061240-f165b6bc-d974-46ce-9178-240379569f14.png)
![menu2](https://user-images.githubusercontent.com/55768917/149059741-ece2eb87-cd1f-40fa-99b4-9b02fca71843.png)

### Add a Buddy Information
An address book entry is composed of a name, address, and phone number.

![add](https://user-images.githubusercontent.com/55768917/149059751-66947a46-9d3c-4cb0-a6f7-30553fd5d72f.png)

### Buddy Information Entry
Note that the "#" is used as delimiters.

![main2](https://user-images.githubusercontent.com/55768917/149059743-a4fa07e1-e928-4f68-b2ff-db3796d58223.png)

### Edit/Remove Buddy Information Entry
To modify an current address book entry, this can be done using the <strong><em>edit</em></strong>. 
Or select the <strong><em>remove</em></strong> menu item to delete an entry.

![menu3](https://user-images.githubusercontent.com/55768917/149059745-f7b2c745-2a68-485d-9e3d-79b05a4c4817.png)

### Save/Load Address Book
The current state of the address book can be store via the <strong><em>save</em></strong> feature which exports the address book via an XML format.
Then any address book can be loaded back using the <strong><em>import</em></strong> feature using a SAX parser for XML.

![menu4](https://user-images.githubusercontent.com/55768917/149059748-974f1e80-3b76-4d04-9faa-3d6f605744a1.png)

### Exiting the Application
When done, exit the program.

![main3](https://user-images.githubusercontent.com/55768917/149059749-208bc11d-8ff7-4093-a43e-a053209ed2aa.png)

## Acknowledgement

Prof and TAs for feedback on this application.

## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, 
allowance is made for “fair use” for purposes such as criticism, 
comment, news reporting, teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise 
be infringing.
